package org.example.test.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.log4j.Logger;
import org.example.test.utils.ByteArrayUtil;

public class ClientNIO {
	
	private static final String CARRIAGE_RETURN = "\r";

	private static final Logger LOGGER = Logger.getLogger(ClientNIO.class);

    public SocketChannel client = null;

    public RecvThread rt = null;

    public ClientNIO() {
    }

    public void connect(String hostname, int port) {
    	InetSocketAddress inetSocketAddress = null;
//        int result = 0;
        try {
            inetSocketAddress = new InetSocketAddress(hostname, port);
            
            client = SocketChannel.open();
            client.connect(inetSocketAddress);
            client.configureBlocking(false);
            LOGGER.info("Connected to " + hostname + ":" + port);
            receiveMessage();
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        while ((result = sendMessage()) != -1) {
//        }
//
//        try {
//            client.close();
//            System.exit(0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    
    public void close() throws IOException {
    	 LOGGER.info("time to stop the client");
         interruptThread();
         try {
             Thread.sleep(5000);
         } catch (Exception e) {
             e.printStackTrace();
         }
         client.close();
    }
    
    public int sendMessage() {
        //System.out.println("Inside SendMessage");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String message = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int nBytes = 0;
        try {
            message = in.readLine();

            if (message.equals("quit") || message.equals("shutdown")) {
               
                return -1;
            } else {
            	message = message + CARRIAGE_RETURN;
            	LOGGER.debug(String.format("TX(% 3d) -> %s [%s]", message.length(), ByteArrayUtil.toHexadecimal(message.getBytes()), ByteArrayUtil.toASCII(message.getBytes())));
            	buffer = ByteBuffer.wrap(message.getBytes());
                nBytes = client.write(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return nBytes;
    }
    
   

    public void receiveMessage() {
        rt = new RecvThread(client);
        rt.start();

    }

    public void interruptThread() {
        rt.val = false;
    }

    public class RecvThread extends Thread {

        public SocketChannel socketChannel = null;
        public boolean val = true;

        public RecvThread(SocketChannel sc) {
            super();
            socketChannel = sc;
        }

        public void run() {

            //System.out.println("Inside receivemsg");
            int nBytes = 0;
            ByteBuffer buffer = ByteBuffer.allocate(2048);
            try {
                while (val) {
                    while ((nBytes = client.read(buffer)) > 0) {
                        buffer.flip();
                        Charset charset = Charset.forName("us-ascii");
                        CharsetDecoder decoder = charset.newDecoder();
                        CharBuffer charBuffer = decoder.decode(buffer);
                        String result = charBuffer.toString();
                        //System.out.println(result);
                        
                        LOGGER.debug(String.format("RX(% 3d) <- %s [%s]", nBytes, ByteArrayUtil.toHexadecimal(result.getBytes()), ByteArrayUtil.toASCII(result.getBytes())));
                        
                        buffer.flip();

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();

            }


        }
    }
}