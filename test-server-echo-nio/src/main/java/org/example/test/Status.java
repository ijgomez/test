package org.example.test;


public enum Status {

	DISCONNECTED((byte) 0x00), 
	CONNECTING((byte) 0x01), 
	CONNECTED((byte) 0x02), 
	DISCONNECTING((byte) 0x03);
    
    private byte status;
    
    private Status(byte status) {
    	this.status = status;
	}
    
    public byte getStatus() {
		return status;
	}
    
}
