package org.example.test.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

public class Client {

	private static final Logger LOGGER = Logger.getLogger(Client.class);

	public void connect(String hostname, int port) {
		// declaration section:
		// smtpClient: our client socket
		// os: output stream
		// is: input stream
		Socket socket = null;
		DataOutputStream os = null;
		// DataInputStream is = null;
		BufferedReader is = null;
		// Initialization section:
		// Try to open a socket on port 25
		// Try to open input and output streams
		try {
			socket = new Socket(hostname, port);
			os = new DataOutputStream(socket.getOutputStream());
			// is = new DataInputStream(socket.getInputStream());
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + hostname);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: " + hostname);
		}
		// If everything has been initialized then we want to write some data
		// to the socket we have opened a connection to on port 25
		if (socket != null && os != null && is != null) {
			try {
				// The capital string before each colon has a special meaning to
				// SMTP
				// you may want to read the SMTP specification, RFC1822/3
				// os.writeBytes("HELO\n");
				// os.writeBytes("MAIL From: k3is@fundy.csd.unbsj.ca\n");
				// os.writeBytes("RCPT To: k3is@fundy.csd.unbsj.ca\n");
				// os.writeBytes("DATA\n");
				// os.writeBytes("From: k3is@fundy.csd.unbsj.ca\n");
				// os.writeBytes("Subject: testing\n");
				// os.writeBytes("Hi there\n"); // message body
				// os.writeBytes("\n.\n");
				// os.writeBytes("QUIT");

				os.write("ATZ\r".getBytes());

				// keep on reading from/to the socket till we receive the "Ok"
				// from SMTP,
				// once we received that then we want to break.
				String responseLine;
				while ((responseLine = is.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("Ok") != -1) {
						break;
					}
				}
				// clean up:
				// close the output stream
				// close the input stream
				// close the socket
				os.close();
				is.close();
				socket.close();
			} catch (UnknownHostException e) {
				System.err.println("Trying to connect to unknown host: " + e);
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}
	}

	public static void main(String[] args) {
		new Client().connect("localhost", 9000);
	}

}
