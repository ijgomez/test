package org.example.test.controllers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.apache.log4j.Logger;

public class CommunicationService {

	public static final CommunicationService INSTANCE = new CommunicationService();
	
	private static final Logger LOGGER = Logger.getLogger("COMMUNICATION");
	
	private boolean connect = false;
	
	private TcpClient client;

	
	private String hostname;
	
	private int port;
	
	/**
	 * New Instance
	 */
	private CommunicationService() {

	}
	
	public void openConnection(String hostname, int port) {
		try {
			this.hostname = hostname;
			this.port = port;	
			
			client = new TcpClient();
			client.connect(new InetSocketAddress(this.hostname, this.port));
			connect = true;
			
			LOGGER.info("client.isConnected="  + client.isConnected());
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(byte[] message) {

		ByteBuffer buffer = ByteBuffer.allocate(65535);

		try {
			buffer.put(message);
			buffer.flip();
			client.send(buffer);

			buffer.clear();

		} catch (Exception e) {
			LOGGER.error("exception: " + e.getMessage());

		}

	}
	

	public void closeConnection() {
		if (client.isConnected()) {
			try {
				client.disconnect();
				connect = false;
				LOGGER.info("client disconnected.");
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public boolean isConnect() {
		return connect;
	}

	
}
