package org.example.test.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.swing.SwingWorker;

public class Decode64Worker extends SwingWorker<Void, File> {
	
	private String directory;
	
	public Decode64Worker(String directory) {
		this.directory = directory;
	}

	@Override
	protected Void doInBackground() throws Exception {
		System.out.println("decoding...");
		File targetFile = new File("file_" + System.currentTimeMillis());
		System.out.println(directory);
		 //byte[] decodedBytes = Base64.getDecoder().decode(loadFileAsBytesArray(directory));
		byte[] decodedBytes = Base64.getDecoder().decode(directory);

		 System.out.println((decodedBytes != null)? decodedBytes.length : "null");
	        writeByteArraysToFile(targetFile, decodedBytes);
		System.out.println(targetFile.length());
		publish(targetFile);
		
		return null;
	}
	
	 public static byte[] loadFileAsBytesArray(String fileName) throws Exception {
		 
	        File file = new File(fileName);
	        int length = (int) file.length();
	        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
	        byte[] bytes = new byte[length];
	        
	        System.out.println("reading " + fileName + " ") ;
	        reader.read(bytes, 0, length);
	        reader.close();
	        return bytes;
	 
	    }
	
	public static void writeByteArraysToFile(File file, byte[] content) throws IOException {

        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
        writer.write(content);
        writer.flush();
        writer.close();
 
    }
	
	@Override
	protected void process(List<File> chunks) {
//		for (String msg : chunks) {
//			// TODO Auto-generated method stub
////			 displayResult(msg);
//		}
	}

}
