package org.example.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

	private static final Logger LOGGER = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		LOGGER.trace("Executing Main.");
		(new Main()).run(args);
	}
	
	public Main() {
		super();
		LOGGER.debug("Build Main.");
	}
	
	private void run(String[] args) {
		LOGGER.info("Run...");
		try {
			// TODO Auto-generated method stub
			
		} catch (Exception e) {
			LOGGER.fatal("Generic Error: ", e);
		}
		LOGGER.info("...end.");

	}
}
