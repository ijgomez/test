package org.example.test;

import java.io.IOException;

import org.example.test.server.EchoServerNIO;

public class Main {

	public static void main(String[] args) {
		try {
			(new EchoServerNIO(null, 9000)).startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
