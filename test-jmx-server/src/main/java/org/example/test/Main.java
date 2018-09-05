package org.example.test;

public class Main {

	public static void main(String[] args) {
		TestBeanServer server;
		
		try {
			server = new TestBeanServer();
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
