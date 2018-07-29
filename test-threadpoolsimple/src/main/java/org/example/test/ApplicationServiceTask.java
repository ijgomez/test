package org.example.test;

import org.apache.log4j.Logger;

public class ApplicationServiceTask implements Runnable {

	private static Logger LOGGER = Logger.getLogger(ApplicationServiceTask.class);

	private int line;

	public ApplicationServiceTask(int l) {
		this.line = l;
	}

	public void run() {
		try {
			LOGGER.info("Procesando Linea: " + line);
			
			// TODO Auto-generated method stub
			Thread.sleep(5*1000);
			
			
		} catch (Exception e) {
			LOGGER.error("Fallo al procesar la linea " + line, e);
		}
	}

}
