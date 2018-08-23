package org.example.test.layers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;
import javax.comm.CommPortOwnershipListener;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

import org.example.test.helper.ByteArrayUtil;

public class SerialPortLayer implements PhisicalLayer {

	private String portName;
	
	private int timeout;
		
	private CommPort commPort;
	
	private SerialPort serialPort;
	
	private OutputStream output;
	
    private InputStream input;
	
	public SerialPortLayer(String portName) {
		this.portName = portName;
		this.timeout = 10000;
	}
	
	public void connect() throws IOException {
		try {
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
			portIdentifier.addPortOwnershipListener(new CommPortOwnershipListener() {
				
				public void ownershipChange(int type) {
					
					if (type == CommPortOwnershipListener.PORT_OWNED)
						System.out.println("Event: " + SerialPortLayer.this.portName + ". ownershipChange (type=" + type + ") Port open");
					else if (type == CommPortOwnershipListener.PORT_UNOWNED)
						System.out.println("Event: " + SerialPortLayer.this.portName + ". ownershipChange (type=" + type + ") Port close");
					else if (type == CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED)
						System.out.println("Event: " + SerialPortLayer.this.portName + ". ownershipChange (type=" + type + ") Port in use");
					else 
						System.out.println("Event: " + SerialPortLayer.this.portName + ". ownershipChange (type=" + type + ")");
					
				}
			});
			if (portIdentifier.isCurrentlyOwned()) {
				System.err.println("Error: " + portName + ". Port is currently in use");
			} else {
				commPort = portIdentifier.open(this.getClass().getName(), timeout);
				System.out.println("Info: " + portName + ". ...open port.");
				if (commPort instanceof SerialPort) {
					serialPort = (SerialPort) commPort;
					
					
					
					serialPort.addEventListener(new SerialPortEventListener() {
						
						public void serialEvent(SerialPortEvent event) {
							System.out.println("Event: " + SerialPortLayer.this.portName + ". serialEvent (type=" + event.getEventType() + ") -> newValue=" + event.getNewValue() + ", oldValue=" + event.getOldValue() + ", source=" + event.getSource());
							switch (event.getEventType()) {
					            case SerialPortEvent.BI:
					            case SerialPortEvent.CD:
					            case SerialPortEvent.CTS:
					            case SerialPortEvent.DSR:
					            case SerialPortEvent.FE:
					            case SerialPortEvent.OE:
					            case SerialPortEvent.PE:
					            case SerialPortEvent.RI:
//					            	break;
//					            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
//					            	System.out.println("Info: " + SerialPortLayer.this.portName + ". RX (0) <- ¡BUFFER EMPTY!");
					                break;
					            case SerialPortEvent.DATA_AVAILABLE:
									try {
										receive();
									} catch (IOException e) {
										e.printStackTrace();
									}
					            	break;
					            default:
					                break;
							}
						}
					});
					
					serialPort.notifyOnBreakInterrupt(true);
                    serialPort.notifyOnCarrierDetect(true);
                    serialPort.notifyOnCTS(true);
                    serialPort.notifyOnDataAvailable(true);
                    serialPort.notifyOnDSR(true);
                    serialPort.notifyOnFramingError(true);
                    serialPort.notifyOnOutputEmpty(true);
                    serialPort.notifyOnOverrunError(true);
                    serialPort.notifyOnParityError(true);
                    serialPort.notifyOnRingIndicator(true);

                    //TODO
                    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
					serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
//					serialPort.enableReceiveThreshold(1);
//					serialPort.enableReceiveTimeout(timeout);
					serialPort.setDTR(true);
					serialPort.setRTS(true);
					
					System.out.println("Info: " + portName + ". ...configured port.");
                    
					input = serialPort.getInputStream();
					output = serialPort.getOutputStream();
					
					System.out.println("Info: " + portName + ". ...open and configured connection.");
					
				} else {
					System.err.println("Error: " + portName + ". Only serial ports are handled.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isConnected(){
		return (this.commPort != null);
	}

	public void send(byte[] data) throws IOException {
		if (this.isConnected()) {
			System.out.println("Info: " + portName + ". isDTR=" + serialPort.isDTR() + ", isRTS=" + serialPort.isRTS() + ", isDSR=" + serialPort.isDSR() + ", isCTS=" + serialPort.isCTS());
			System.out.println("Info: " + portName + ". TX (" + data.length + ") -> " + ByteArrayUtil.toHexadecimal(data) + " (" + ByteArrayUtil.toASCII(data) + ")");
			this.output.write(data);
		} else {
			System.err.println("Error: the connection is closed.");
		}
	}

	public void receive() throws IOException {
		int readBytes;
		byte[] buffer;

		readBytes = input.available();
		System.out.println("Info: " + portName + ". read bytes = " + readBytes);
		if (readBytes > 0) {
			buffer = new byte[readBytes];
			input.read(buffer, 0, readBytes);
			System.out.println("Info: " + portName + ". RX (" + buffer.length + ") <- " + ByteArrayUtil.toHexadecimal(buffer) + " (" + ByteArrayUtil.toASCII(buffer) + ")");
		}
		
		
	}

	public void disconnect() throws IOException {
		if (isConnected()) {
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.close();
			}
			serialPort.close();
			commPort.close();
			
			serialPort = null;
			commPort = null;
			System.out.println("Info: " + portName + ". ...Closed Connection.");
		} 
	}
	
}
