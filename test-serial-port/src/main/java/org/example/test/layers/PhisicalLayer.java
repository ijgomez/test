package org.example.test.layers;

import java.io.IOException;

public interface PhisicalLayer {

	void connect() throws IOException;

	void send(byte[] data) throws IOException;

	void receive() throws IOException;

	void disconnect() throws IOException;

	boolean isConnected();
}