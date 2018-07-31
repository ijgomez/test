package org.example.test.fwk.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

public class Fichero {
	private static int bufferSize = 4 * 1024;
	public static long generarChecksumAdler32(InputStream is) throws IOException, FileNotFoundException {
		try {
			CheckedInputStream cis = new CheckedInputStream(is, new Adler32());
			byte[] tempBuf = new byte[bufferSize];
	        while (cis.read(tempBuf) >= 0) {
	        }
	        long checksum = cis.getChecksum().getValue();
	        return checksum;
		} catch (FileNotFoundException e) {
			throw e;
			
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static long generarChecksumCRC32(InputStream is) throws IOException, FileNotFoundException {
		try {
			CheckedInputStream cis = new CheckedInputStream(is, new CRC32());
			byte[] tempBuf = new byte[bufferSize];
	        while (cis.read(tempBuf) >= 0) {
	        }
	        long checksum = cis.getChecksum().getValue();
	        return checksum;
		} catch (FileNotFoundException e) {
			throw e;
			
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private static boolean clock = true;

	public static long createChecksum(InputStream is) throws IOException {
		long millis = System.currentTimeMillis();

		CRC32 checksum = new CRC32();
		checksum.reset();
		byte[] buffer = new byte[bufferSize];
		int bytesRead;
		while ((bytesRead = is.read(buffer)) >= 0) {
			checksum.update(buffer, 0, bytesRead);
		}
		is.close();
		if (clock) {
			millis = System.currentTimeMillis() - millis;
			System.out.println("Second(s): " + (millis/1000L));
		}
		return checksum.getValue();
	}

	
	
}
