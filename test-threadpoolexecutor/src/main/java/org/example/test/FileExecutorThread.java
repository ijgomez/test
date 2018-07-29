package org.example.test;

import java.io.File;

import org.apache.log4j.Logger;

public class FileExecutorThread implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(FileExecutorThread.class);
	
	private File f;
	
	public FileExecutorThread(File file) {
		this.f = file;
	}
	

	public void run() {
		long t = System.currentTimeMillis();
		LOGGER.info("Start. Command = " + this.f);
		try {
			this.processCommand();
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		LOGGER.info("End. Command = " + this.f + ". " + (System.currentTimeMillis() - t) + " ms.");
	}

	private void processCommand() throws Exception {
		Thread.sleep(5000);
	}

}
