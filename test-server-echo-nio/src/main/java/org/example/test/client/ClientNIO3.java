package org.example.test.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.example.test.utils.ByteArrayUtil;

public class ClientNIO3 {
	
	private static final Logger LOGGER = Logger.getLogger(ClientNIO3.class);

	public static void main(String[] args) {
		
		ClientNIO3 client = new ClientNIO3();
		
		try {
			client.connectM("localhost", 9000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LOGGER.info("finnished!!!");
		
		
		
	}
	private static byte DISCONNECTED = 0x00;
	private static byte CONNECTING = 0x01;
	private static byte CONNECTED = 0x02;
	private static byte DISCONNECTING = 0x03;
	
	private SocketChannel socketChannel;
	
	private byte state = DISCONNECTED;
	private long timeout = 30000;
	
	public void connectM(final String address, final int port) throws IOException {
		connect(address, port);
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					connect(address, port);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		}).start();
	}

//	private void connect(final String address, final int port) throws IOException {
//
//		Selector selector = null;
//		Set<SelectionKey> keys = null;
//		
//		try {
//			socketChannel = SocketChannel.open();
//			
//			LOGGER.info("Connecting to " + address + ":" + port + "...");
//			socketChannel.connect(new InetSocketAddress(address, port));
//			socketChannel.configureBlocking(false);
////
////			selector = Selector.open();
////			
////			int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT; 
////			
////			socketChannel.register(selector, interestSet);
////			
////			LOGGER.debug("Waiting for the connection ... ");
////						
////			// Waiting for the connection
////			while (selector.select() > 0) {
////				keys = selector.selectedKeys();
////				LOGGER.debug("keys: " + keys.size());
////
////				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
////
////				while(iterator.hasNext()) {
////				    
////				    SelectionKey key = iterator.next();
////				    LOGGER.debug("key:" + key);
////				    if(key.isAcceptable()) {
////				        // a connection was accepted by a ServerSocketChannel.
////				    	LOGGER.debug("a connection was accepted by a ServerSocketChannel...");
////				    } else if (key.isConnectable()) {
////				        // a connection was established with a remote server.
////				    	LOGGER.debug("a connection was established with a remote server....");
////				    } else if (key.isReadable()) {
////				        // a channel is ready for reading
////				    	read(key);
////				    } else if (key.isWritable()) {
////				        // a channel is ready for writing
////				    	LOGGER.debug("a channel is ready for writing....");
////				    }
////
////				    iterator.remove();
////				}
////			}
//			
//			selector = Selector.open();
//
//			//SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT);
//			SelectionKey key = socketChannel.register(selector, SelectionKey.OP_CONNECT);
//			
//			LOGGER.debug("Waiting for the connection ... ");
//			while (true) {
//
//				int readyChannels = selector.select();
//
//				LOGGER.debug("state=" + state + ", readyChannels=" + readyChannels);
//
//				if(readyChannels == 0) continue;
//
//				Set<SelectionKey> selectedKeys = selector.selectedKeys();
//				
//				Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
//
//				while(keyIterator.hasNext()) {
//
//					SelectionKey selectionKey = keyIterator.next();
//
//					if (!selectionKey.isValid()) {
//						LOGGER.warn("key is invalid!");
//						break;
//					} else {
//						LOGGER.debug("selectionKey=" + selectionKey);
//					}
//
//					if (selectionKey.isAcceptable()) {
//						// a connection was accepted by a ServerSocketChannel.
//						LOGGER.warn("a connection was accepted by a ServerSocketChannel...");
//						
//					} else if (selectionKey.isConnectable()) {
//						// a connection was established with a remote server.
//						LOGGER.debug("a connection was established with a remote server....");
//						
//						LOGGER.info("selectionKey.interestOps()=" + selectionKey.interestOps());
//						
////						key.interestOps(key.interestOps() ^ SelectionKey.OP_CONNECT);
////					    key.interestOps(key.interestOps() | SelectionKey.OP_READ);
//
//						state = CONNECTED;
//
//					} else if (selectionKey.isReadable()) {
//						// a channel is ready for reading
//						read(selectionKey);
//					} else if (selectionKey.isWritable()) {
//						// a channel is ready for writing
//						write(selectionKey);
//					}
//					keyIterator.remove();
//					
//				}
//			}
//			
//			
//			//TODO
//			
//		} finally {
//			//TODO
//			
//		}
//		
//		
//	}
	
	private void connect(final String address, final int port) throws IOException {

		Selector selector = null;
		Set<SelectionKey> keys = null;
		
		try {
			socketChannel = SocketChannel.open();
			
			LOGGER.info("Connecting to " + address + ":" + port + "...");
			socketChannel.connect(new InetSocketAddress(address, port));
			socketChannel.configureBlocking(false);
			
			selector = Selector.open();

			//SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT);
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			
			LOGGER.debug("Waiting for the connection ... ");
			
			if (selector.select(timeout) > 0) {
				// Get keys
				keys = selector.selectedKeys();

				LOGGER.debug("keys: " + keys.size());
				for (SelectionKey key : keys) {
					keys.remove(key);
					
					// Attempt a connection
					if (key.isValid() && key.isConnectable()) {
						// Connection OK
						LOGGER.debug("Connection was established with a remote server....");
						
						key.interestOps(key.interestOps() ^ SelectionKey.OP_CONNECT);
						key.interestOps(key.interestOps() | SelectionKey.OP_READ);

						state = CONNECTED;
						
					}
					
				}
			}
			
			
			//TODO
			
		} finally {
			//TODO
			
		}
		
		
	}
	
	
	private void read(SelectionKey key) throws IOException {
		LOGGER.debug(" a channel is ready for reading....");
		// TODO Auto-generated method stub
//		ByteBuffer buffer = ByteBuffer.allocate(1024);
//		
//		while (socketChannel.read(buffer) > 0) {
//			// flip the buffer to start reading
//			buffer.flip();
//
//			byte[] result = new byte[buffer.remaining()];
//			buffer.get(result);
//			
//			LOGGER.debug(String.format("RX(% 3d) <- %s [%s]", result.length, ByteArrayUtil.toHexadecimal(result), ByteArrayUtil.toASCII(result)));
//			
//			buffer.rewind();
//		}
	}
	
	private void write(SelectionKey key) throws IOException {
		LOGGER.debug("a channel is ready for writing....");
		
		
//		String newData = "New String to write to file..." + System.currentTimeMillis() + "\r\n";
//
//		LOGGER.debug(String.format("RX(% 3d) -> %s [%s]", newData.length(), ByteArrayUtil.toHexadecimal(newData.getBytes()), ByteArrayUtil.toASCII(newData.getBytes())));
//		
//		ByteBuffer buffer = ByteBuffer.allocate(1024);
//		buffer.clear();
//		buffer.put(newData.getBytes());
//		buffer.flip();
//
//		while(buffer.hasRemaining()) {
//			socketChannel.write(buffer);
//		}
		
		
	}

	public void close() throws IOException {
		if (socketChannel != null) {
			LOGGER.info("Close connection... ");
			socketChannel.close();
		}
	}
}
