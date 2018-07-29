package org.example.test;

import java.io.IOException;

import org.example.test.server.ServidorHTTP;

public class Main  {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		Main.runHTTPServer();
		
	}
	
	private static void runHTTPServer() {
		try {
			ServidorHTTP servidor = new ServidorHTTP();
			servidor.configurar();
			servidor.iniciar();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
}
