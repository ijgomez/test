package org.example.test.helper;

import java.util.Enumeration;

import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.ParallelPort;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;

public class CommPortHelper {

	public static void commPortsDetected() {
		System.out.println("Ports detected:");
		
		Enumeration<?> portIdentifiers = CommPortIdentifier.getPortIdentifiers();
		while (portIdentifiers.hasMoreElements()) {
			CommPortIdentifier commPortIdentifier = (CommPortIdentifier) portIdentifiers.nextElement();
			System.out.println("\tPort: " + commPortIdentifier.getName() + " [type=" + commPortIdentifier.getPortType() + ", currentOwner=" + commPortIdentifier.getCurrentOwner() + "]");
		}
	}
	
	public static void printDetails(String portName) {
		System.out.println("Details of port: " + portName);
		try {
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
			if (portIdentifier.isCurrentlyOwned()) {
				System.out.println("Error: Port is currently in use");
			} else {
				CommPort commPort = portIdentifier.open(CommPortHelper.class.getName(), 2000);
				
				printDetails(commPort);
				
				commPort.close();
			}
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public static void printDetails(CommPort port) {
		
		System.out.println("[" + port.getName() + "] CommPort.Name=" + port.getName());
		
		System.out.println("[" + port.getName() + "] CommPort.isReceiveFramingEnabled=" + port.isReceiveFramingEnabled());
		System.out.println("[" + port.getName() + "] CommPort.ReceiveFramingByte=" + port.getReceiveFramingByte());
		System.out.println("[" + port.getName() + "] CommPort.isReceiveThresholdEnabled=" + port.isReceiveThresholdEnabled());
		System.out.println("[" + port.getName() + "] CommPort.ReceiveThreshold=" + port.getReceiveThreshold());
		System.out.println("[" + port.getName() + "] CommPort.isReceiveTimeoutEnabled=" + port.isReceiveTimeoutEnabled());
		System.out.println("[" + port.getName() + "] CommPort.ReceiveTimeout=" + port.getReceiveTimeout());

    	System.out.println("[" + port.getName() + "] CommPort.InputBufferSize=" + port.getInputBufferSize());
    	System.out.println("[" + port.getName() + "] CommPort.OutputBufferSize=" + port.getOutputBufferSize());
    	
		if (port instanceof SerialPort) {
			SerialPort serialPort = (SerialPort) port;
			
			System.out.println("[" + port.getName() + "] SerialPort.BaudRate=" + serialPort.getBaudRate());
	    	System.out.println("[" + port.getName() + "] SerialPort.DataBits=" + serialPort.getDataBits());
	    	System.out.println("[" + port.getName() + "] SerialPort.FlowControlMode=" + serialPort.getFlowControlMode());
	    	System.out.println("[" + port.getName() + "] SerialPort.Parity=" + serialPort.getParity());
	    	System.out.println("[" + port.getName() + "] SerialPort.StopBits=" + serialPort.getStopBits());
	    	
		} else if (port instanceof ParallelPort) {
			ParallelPort parallelPort = (ParallelPort) port;
			
			System.out.println("[" + port.getName() + "] ParallelPort.mode=" + parallelPort.getMode());
			System.out.println("[" + port.getName() + "] ParallelPort.outputBufferFree=" + parallelPort.getOutputBufferFree());

		}
		
    	
	}
	
}
