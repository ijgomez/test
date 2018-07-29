package org.example.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class Main {
	
	private static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		
		LOGGER.info("--------------------------------------------------------------------------");
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			
			executorService.execute(new FileExecutorThread(null));
			
		}
		executorService.shutdown();
		while (!executorService.isTerminated()) {
		}
		
		LOGGER.info("--------------------------------------------------------------------------");
		
	}
}
