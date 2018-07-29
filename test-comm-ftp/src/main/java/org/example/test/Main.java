package org.example.test;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String host = "localhost";
		int port = 21;
		
		String user = "energia";
		String password = "temporal";
		
		String localPathname = "src/test/resources/forecast.csv";
		String remoteFilename = "forecast.csv";
		
		
		try {
			FTPHelper.storeFile(host, port, user, password, localPathname, remoteFilename);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
