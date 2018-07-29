package org.example.test;

import java.io.IOException;

import org.example.test.client.ClientNIO;

public class ModemClientNIO extends ClientNIO {

	
	@Override
	public void connect(String hostname, int port) {
		super.connect(hostname, port);
	}
	
	public static void main(String[] args) {
		int result = 0;
		ModemClientNIO client = null;

		try {

			client = new ModemClientNIO();
			client.connect("localhost", 9000);
			//client.connect("192.168.253.165", 5001);

			while ((result = client.sendMessage()) != -1) {
			}

			client.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.exit(0);
	}
}
