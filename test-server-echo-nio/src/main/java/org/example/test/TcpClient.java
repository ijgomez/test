package org.example.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.log4j.Logger;
import org.example.test.utils.ByteArrayUtil;

/**
 * A simple NIO TCP client
 * Assumptions:
 * - the client should always be connected,
 *   once it gets disconnected it reconnects
 * - the exception thrown by onRead means protocol error
 *   so client disconnects and reconnects
 * - the incoming flow is higher than outgoing, so
 *   direct channel write method is not implemented
 * 
 * @author Vladimir Lysyy (mail@bobah.net)
 *
 */
public class TcpClient implements Runnable {
	
	private static final Logger LOG = Logger.getLogger(TcpClient.class);
	
	private static final long INITIAL_RECONNECT_INTERVAL = 1000; // 1 sec.
	private static final long MAXIMUM_RECONNECT_INTERVAL = 30000; // 30 sec.
	private static final int READ_BUFFER_SIZE = 0x100000;// 1Mbit
	private static final int WRITE_BUFFER_SIZE = 0x100000;// 1Mbit

	private long reconnectInterval = INITIAL_RECONNECT_INTERVAL;

	private ByteBuffer readBuffer = ByteBuffer.allocateDirect(READ_BUFFER_SIZE); 
	private ByteBuffer writeBuffer = ByteBuffer.allocateDirect(WRITE_BUFFER_SIZE); 

	private final Thread thread = new Thread(this);
	private SocketAddress address;

	private Selector selector;
	private SocketChannel channel;

	private final AtomicReference<Status> connected = new AtomicReference<Status>(Status.DISCONNECTED);

	private AtomicLong bytesOut = new AtomicLong(0L);
	private AtomicLong bytesIn = new AtomicLong(0L);
	
	private final Object wait;
	
	private IOException exception;

	public TcpClient() {
		this.wait = new Object();
	}

	public void connect(SocketAddress address) throws IOException, InterruptedException {
		this.address = address;
		
		LOG.info("Connecting to " + this.address);
		connected.set(Status.CONNECTING);
		thread.start();

		LOG.trace("waiting for starting...");
		synchronized (wait) {
			wait.wait();
		}
		if (exception != null) {
			throw exception;
		}

	}

//	public void join() throws InterruptedException {
//		if (Thread.currentThread().getId() != thread.getId()) {
//			thread.join();
//		}
//	}

	public void disconnect() throws IOException, InterruptedException {
		LOG.info("disconneting from " + this.address);
		connected.set(Status.DISCONNECTING);
		thread.interrupt();
		selector.wakeup();
		if (thread.isAlive()) {
			LOG.trace("waiting for stopping...");
			synchronized (wait) {
				wait.wait();
			}
			if (exception != null) {
				throw exception;
			}
		}
	}

	public boolean isConnected() {
		return connected.get().equals(Status.CONNECTED) || connected.get().equals(Status.DISCONNECTING);
	}

	  /**
	   * @param buffer data to send, the buffer should be flipped (ready for read)
	   * @throws InterruptedException
	   * @throws IOException
	   */
	public void send(ByteBuffer buffer) throws InterruptedException, IOException {
		if (!isConnected()) {
			throw new IOException("not connected");
		}
		synchronized (writeBuffer) {
			// try direct write of what's in the buffer to free up space
//			LOG.trace("writeBuffer: position=" + writeBuffer.position() + ", remaining=" + writeBuffer.remaining() + "(" + writeBuffer.hasRemaining() + "), capacity=" + writeBuffer.capacity());
			
			if (writeBuffer.remaining() < buffer.remaining()) {
				writeBuffer.flip();
				int bytesOp = 0, bytesTotal = 0;
				while (writeBuffer.hasRemaining() && (bytesOp = channel.write(writeBuffer)) > 0) {
					bytesTotal += bytesOp;
				}
				
				writeBuffer.flip();
				byte[] result = new byte[writeBuffer.remaining()];
				writeBuffer.get(result);
				LOG.debug(String.format("TX (% 3d) -> %s", bytesTotal, ByteArrayUtil.toHexadecimal(result)));
				
				writeBuffer.compact();
			}

			// if didn't help, wait till some space appears
			if (Thread.currentThread().getId() != thread.getId()) {
				while (writeBuffer.remaining() < buffer.remaining()) {
					writeBuffer.wait();
				}
			} else {
				if (writeBuffer.remaining() < buffer.remaining()) {
					throw new IOException("send buffer full"); // TODO: add reallocation or buffers chain
				}
			}
			writeBuffer.put(buffer);

//			LOG.trace("writeBuffer: position=" + writeBuffer.position() + ", remaining=" + writeBuffer.remaining() + "(" + writeBuffer.hasRemaining() + "), capacity=" + writeBuffer.capacity());
			
			// try direct write to decrease the latency
			writeBuffer.flip();
			int bytesOp = 0, bytesTotal = 0;
			while (writeBuffer.hasRemaining() && (bytesOp = channel.write(writeBuffer)) > 0) {
				bytesTotal += bytesOp;
			}
			
			writeBuffer.flip();
			byte[] result = new byte[writeBuffer.remaining()];
			writeBuffer.get(result);
			LOG.debug(String.format("TX (% 3d) -> %s", bytesTotal, ByteArrayUtil.toHexadecimal(result)));
			
			writeBuffer.compact();
			
			if (writeBuffer.hasRemaining()) {
				SelectionKey key = channel.keyFor(selector);
				key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
				selector.wakeup();
			}
		}
	}

	/**
	 * Override with something meaningful
	 * 
	 * @param buffer
	 */
	protected void onRead(ByteBuffer buffer) throws Exception {};

	/**
	 * Override with something meaningful
	 * 
	 * @param buf
	 */
	protected void onConnected() throws Exception {};

	/**
	 * Override with something meaningful
	 * 
	 * @param buf
	 */
	protected void onDisconnected(){};

	private void configureChannel(SocketChannel channel) throws IOException {
		channel.configureBlocking(false);
		channel.socket().setSendBufferSize(0x100000); // 1Mb
		channel.socket().setReceiveBufferSize(0x100000); // 1Mb
		channel.socket().setKeepAlive(true);
		channel.socket().setReuseAddress(true);
		channel.socket().setSoLinger(false, 0);
		channel.socket().setSoTimeout(0);
		channel.socket().setTcpNoDelay(true);
	}

	@Override
	public void run() {
		LOG.trace("event loop running");
		try {
			while (!Thread.interrupted()) { // reconnection loop
				try {
					selector = Selector.open();
					channel = SocketChannel.open();
					
					configureChannel(channel);

					channel.connect(address);
					channel.register(selector, SelectionKey.OP_CONNECT);

					while (!thread.isInterrupted() && channel.isOpen()) { // events multiplexing loop
						if (selector.select() > 0) {
							processSelectedKeys(selector.selectedKeys());
						}
					}
				} catch (IOException e) {
					this.exception = e;
					LOG.error("exception", e);
				} finally {
					LOG.trace("isConnected: " + isConnected());
					if (isConnected()) {
						onDisconnected();
						writeBuffer.clear();
						readBuffer.clear();
						if (channel != null) {
							channel.close();
						}
						if (selector != null) {
							selector.close();
						}
						connected.set(Status.DISCONNECTED);
						LOG.info("...connection closed.");
					}
				}
				LOG.trace("thread.state: "  + thread.getState());

				try {
//					LOG.info("sleep " + reconnectInterval + " - " + MAXIMUM_RECONNECT_INTERVAL);
//					Thread.sleep(reconnectInterval);
//					if (connected.get().equals(TCPLayerStatus.CONNECTING)) {
						if (reconnectInterval < MAXIMUM_RECONNECT_INTERVAL) {
							LOG.warn("Reconnecting to " + address + " in " + INITIAL_RECONNECT_INTERVAL + " ms...");
							Thread.sleep(INITIAL_RECONNECT_INTERVAL);
							//reconnectInterval *= 2;
							reconnectInterval = reconnectInterval + INITIAL_RECONNECT_INTERVAL;
							
						} else {
							LOG.error("Connection time exceeded (" + MAXIMUM_RECONNECT_INTERVAL + " ms).");
							//exception = new IOException("timeout reconnect");
	//						synchronized (wait) {
	//							this.wait.notify();	
	//						}
							break;
						}
//					} else {
//						Thread.sleep(INITIAL_RECONNECT_INTERVAL);
//					}
				} catch (InterruptedException e) {
					LOG.trace("interruped thread.");
					break;
				}
			}
		} catch (Exception e) {
			LOG.error("unexpected failure", e);
		}
		LOG.trace("event loop terminated");
		synchronized (wait) {
			this.wait.notify();	
		}
	}

	private void processSelectedKeys(Set<SelectionKey> keys) throws Exception {
		Iterator<SelectionKey> iterator = keys.iterator();
		while (iterator.hasNext()) {
			SelectionKey key = iterator.next();
			if (key.isReadable()) {
				processRead(key);
			}
			if (key.isWritable()) {
				processWrite(key);
			}
			if (key.isConnectable()) {
				processConnect(key);
			}
			if (key.isAcceptable()) {
				;
			}
			iterator.remove();
		}
	}

	private void processConnect(SelectionKey key) throws Exception {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		if (socketChannel.finishConnect()) {
			LOG.info("Connected to " + address);
			key.interestOps(key.interestOps() ^ SelectionKey.OP_CONNECT);
			key.interestOps(key.interestOps() | SelectionKey.OP_READ);
			reconnectInterval = INITIAL_RECONNECT_INTERVAL;
			connected.set(Status.CONNECTED);
			synchronized (wait) {
				this.wait.notify();	
			}
			onConnected();
		}
	}

	private void processRead(SelectionKey key) throws Exception {
		SocketChannel socketChannel = (SocketChannel) key.channel();

		int bytesOp = 0, bytesTotal = 0;
		while (readBuffer.hasRemaining() && (bytesOp = socketChannel.read(readBuffer)) > 0) {
			bytesTotal += bytesOp;
		}

		readBuffer.flip();
		byte[] result = new byte[readBuffer.remaining()];
		readBuffer.get(result);
		
		LOG.debug(String.format("RX (% 3d) <- %s", bytesTotal, ByteArrayUtil.toHexadecimal(result)));

		if (bytesTotal > 0) {
			readBuffer.flip();
			readBuffer.position(readBuffer.limit());
			onRead(readBuffer);
			readBuffer.compact();
		} else if (bytesOp == -1) {
			LOG.error("peer closed read channel");
			socketChannel.close();
		}

		bytesIn.addAndGet(bytesTotal);
	}

	private void processWrite(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		synchronized (writeBuffer) {
			writeBuffer.flip();

			int bytesOp = 0, bytesTotal = 0;
			while (writeBuffer.hasRemaining() && (bytesOp = socketChannel.write(writeBuffer)) > 0) {
				bytesTotal += bytesOp;
			}
			
			writeBuffer.flip();
			byte[] result = new byte[writeBuffer.remaining()];
			writeBuffer.get(result);
			LOG.debug(String.format("TX (% 3d) -> %s", bytesTotal, ByteArrayUtil.toHexadecimal(result)));
			
			bytesOut.addAndGet(bytesTotal);

			if (writeBuffer.remaining() == 0) {
				key.interestOps(key.interestOps() ^ SelectionKey.OP_WRITE);
			}

			if (bytesTotal > 0) {
				writeBuffer.notify();
			} else if (bytesOp == -1) {
				LOG.error("peer closed write channel");
				socketChannel.close();
			}

			writeBuffer.compact();
		}
	}

	

	

	public long getBytesOut() {
		return bytesOut.get();
	}

	public long getBytesIn() {
		return bytesIn.get();
	}


	/**
	 * can be used for testing
	 */
	public static void main(String[] args) throws Exception {

		final TcpClient client;

		try {
			client = new TcpClient();
			client.connect(new InetSocketAddress("127.0.0.1", 9000));
			
			LOG.info("client.isConnected="  + client.isConnected());
			
			if (client.isConnected()) {
				ByteBuffer buffer = ByteBuffer.allocate(65535);

				int count = 0;
				while (count < 10) {

					try {
						buffer.put("SEND".concat(" " + count).getBytes());
						buffer.flip();
						client.send(buffer);
						
						buffer.clear();
						
					} catch (Exception e) {
						LOG.error("exception: " + e.getMessage());
						while (!client.isConnected()) {
							Thread.sleep(1000);
						}
					}
					Thread.sleep(1000);
					count++;
				}
				client.disconnect();
				LOG.info("client disconnected.");
			}
		} catch (IOException e) {
			LOG.error("Fail to connect:", e);
		}
	}

}
