package org.example.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.example.test.helper.CommPortHelper;
import org.example.test.layers.PhisicalLayer;
import org.example.test.layers.SerialPortLayer;

public class Main {

	private static final String CR = "\r";
	
	// private static final String LF = "\n";
	
	// private static final String CRLF = CR + LF;

	public static void main(String[] args) {
		
		if (args == null || args.length == 0) {
			System.err.println("Error. Not Argument");
		} else if (args.length == 1) {
			if (args[0].equals("LIST")) {
				CommPortHelper.commPortsDetected();
			} else {
				System.err.println("Error. Argument not valid");
			}
		} else if (args.length == 2) {
			if (args[0].equals("CONNECT")) {
				try {
					connect(args[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (args[0].equals("DETAILS")) {
				CommPortHelper.printDetails(args[1]);
			} else {
				System.err.println("Error. Argument not valid");
			}
		} else {
			System.err.println("Error. Argument not valid");
		}
		System.exit(0);
	}

	private static void connect(String portName) throws Exception {
		PhisicalLayer phisicalLayer = null;
		
		
		try {
			phisicalLayer = new SerialPortLayer(portName);
			
			phisicalLayer.connect();
			
			if (phisicalLayer.isConnected()) {
				InputStreamReader isr = new InputStreamReader(System.in);
		        BufferedReader br = new BufferedReader (isr);
				
		        while (true) {
		        	System.out.print("USER COMMAND: ");
		        	String line = br.readLine();
		        	if (line.startsWith("EXIT")) {
		        		break;
		        	}
		        	System.out.println("Info: " + portName + ". Sending command: " + line);
		        	phisicalLayer.send((line + CR).getBytes());
//		        	Thread.sleep(500);
//		        	phisicalLayer.receive();
		        }
			}
		} finally {
			phisicalLayer.disconnect();
			System.out.println("Info: " + portName + ". ...port disconected.");
		}
	}

}
