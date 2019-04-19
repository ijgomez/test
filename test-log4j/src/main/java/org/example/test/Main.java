package org.example.test;

import org.apache.log4j.Logger;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		if (LOGGER.isTraceEnabled()) LOGGER.trace("Executing Main.");
		(new Main()).run(args);
	}
	
	public Main() {
		super();
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Build Main.");
	}
	
	private void run(String[] args) {
		if (LOGGER.isInfoEnabled()) LOGGER.info("Run...");
		try {
			// TODO Auto-generated method stub
			
		} catch (Exception e) {
			LOGGER.fatal("Generic Error: ", e);
		}
		if (LOGGER.isInfoEnabled()) LOGGER.info("...end.");

	}
}
