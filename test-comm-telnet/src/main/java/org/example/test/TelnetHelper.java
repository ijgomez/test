package org.example.test;

import java.io.IOException;

import org.apache.commons.net.telnet.TelnetClient;

public final class TelnetHelper {

	public static void telnet(String hostname, int port) throws IOException {
		TelnetClient telnet;

        telnet = new TelnetClient();

    	System.out.println("Conectando con " + hostname + "...");
		telnet.connect(hostname, port);
		IOUtil.readWrite(telnet.getInputStream(), telnet.getOutputStream(), System.in, System.out);
		System.out.println("...Desconectando");
		telnet.disconnect();

	}
	
}
