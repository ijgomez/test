package org.example.test.controllers;

public class TcpClientParameters {

	public static final long INITIAL_RECONNECT_INTERVAL = 1000; // 1 sec.
	
	public static final long MAXIMUM_RECONNECT_INTERVAL = 30000; // 30 sec.
	
	public static final int READ_BUFFER_SIZE = 0x100000;// 1Mbit
	
	public static final int WRITE_BUFFER_SIZE = 0x100000;// 1Mbit

}
