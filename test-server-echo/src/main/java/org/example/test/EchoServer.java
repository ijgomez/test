package org.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	private static int port = 9000;

	public static void main(String[] args) {
		ServerSocket serverSocket;
		Socket socket;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Open socket port: " + port);

			socket = serverSocket.accept();
			System.out.println("Conexion establecida con el cliente.");
			System.out.println("Waiting for input.....");

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println("Cliente Dice: " + inputLine);
				out.println("Mensaje Recibido: " + inputLine);

				if (inputLine.equals("Bye."))
					break;
			}

			out.close();
			in.close();
			socket.close();
			serverSocket.close();
			System.out.println("Finish server socket");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
