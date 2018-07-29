package org.example.test.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.example.test.utils.ByteArrayUtil;

public class EchoServerNIO {
	
	private static final Logger LOGGER = Logger.getLogger(EchoServerNIO.class);
	
	//private static final Charset SERVER_CHARSET = Charset.forName("US-ASCII");
	private static final Charset SERVER_CHARSET = Charset.defaultCharset();
	
	private static final String WELCOME_MESSAGE = "Welcome, conected to echo server\r\n";

	private InetAddress addr;
	
	private int port;
	
	private Selector selector;
	
	private Map<SocketChannel, List<byte[]>> dataMap;

	public EchoServerNIO(InetAddress addr, int port) throws IOException {
		this.addr = addr;
		this.port = port;
		dataMap = new HashMap<SocketChannel, List<byte[]>>();
	}

	public void startServer() throws IOException {
		// create selector and channel
		this.selector = Selector.open();
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);

		// bind to port
		InetSocketAddress listenAddr = new InetSocketAddress(this.addr,
				this.port);
		serverChannel.socket().bind(listenAddr);
		serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);

		LOGGER.info("Server is running in port " + this.port + "... Ctrl-C to stop. [charset: " + SERVER_CHARSET.displayName() + "]");

		// processing
		while (true) {
			// wait for events
			this.selector.select();

			// wakeup to work on selected keys
			Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
			
			while (keys.hasNext()) {
				SelectionKey key = (SelectionKey) keys.next();

				// this is necessary to prevent the same key from coming up
				// again the next time around.
				keys.remove();

				if (!key.isValid()) {
					continue;
				}

				if (key.isAcceptable()) {
					this.accept(key);
				} else if (key.isReadable()) {
					this.read(key);
				} else if (key.isWritable()) {
					this.write(key);
				}
			}
		}
	}
	
	

	private void accept(SelectionKey key) throws IOException {
		ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
		SocketChannel channel = serverChannel.accept();
		channel.configureBlocking(false);


		
		// write welcome message
		
		channel.write(ByteBuffer.wrap(WELCOME_MESSAGE.getBytes(SERVER_CHARSET)));

		Socket socket = channel.socket();
		SocketAddress remoteAddr = socket.getRemoteSocketAddress();
		LOGGER.info("Connected to: " + remoteAddr);

		// register channel with selector for further IO
		dataMap.put(channel, new ArrayList<byte[]>());
		channel.register(this.selector, SelectionKey.OP_READ);
	}

	private void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();

		ByteBuffer buffer = ByteBuffer.allocate(8192);
		int numRead = -1;
		try {
			numRead = channel.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (numRead == -1) {
			this.dataMap.remove(channel);
			Socket socket = channel.socket();
			SocketAddress remoteAddr = socket.getRemoteSocketAddress();
			LOGGER.info("Connection closed by client: " + remoteAddr + " ");
			channel.close();
			key.cancel();
			return;
		}

		byte[] data = new byte[numRead];
		System.arraycopy(buffer.array(), 0, data, 0, numRead);
		String message = ByteArrayUtil.toASCII(data);
		LOGGER.debug(String.format("RX(% 3d) <- %s [%s]", data.length, ByteArrayUtil.toHexadecimal(data), message));

		// write back to client
		//doEcho(key, data);
		doEcho(key, "OK ".concat(message).getBytes());
	}

	private void write(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		List<byte[]> pendingData = this.dataMap.get(channel);
		Iterator<byte[]> items = pendingData.iterator();
		while (items.hasNext()) {
			byte[] data = items.next();
			items.remove();
			LOGGER.debug(String.format("TX(% 3d) -> %s", data.length, ByteArrayUtil.toHexadecimal(data)));
			channel.write(ByteBuffer.wrap(data));
		}
		key.interestOps(SelectionKey.OP_READ);
	}

	private void doEcho(SelectionKey key, byte[] data) {
		SocketChannel channel = (SocketChannel) key.channel();
		List<byte[]> pendingData = this.dataMap.get(channel);
		pendingData.add(data);
		key.interestOps(SelectionKey.OP_WRITE);
	}

}

