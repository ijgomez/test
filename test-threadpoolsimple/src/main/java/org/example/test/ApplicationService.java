package org.example.test;

import org.apache.log4j.Logger;
import org.example.test.pool.ThreadPoolManager;

public class ApplicationService {
	
	private static final Logger LOGGER = Logger.getLogger(ApplicationService.class);
	
	public final void execute() {
		int threads = 8;
		int lines = 60;

		long t = System.currentTimeMillis();
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		try {
			ThreadPoolManager threadPool = new ThreadPoolManager(threads);
			threadPool.start();
			for (int i = 0; i < lines; i++) {
				try {
					
					threadPool.submit(new ApplicationServiceTask(i + 1));
										
				} catch (Exception e) {
					LOGGER.error("Fallo al registrar la tarea para la linea " + (i + 1), e);
				}
			}
			threadPool.waitAndStop();
		} catch (InterruptedException e) {
			LOGGER.error("Fallo al parar el ThreadPool", e);
		}
		LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		LOGGER.info("Proceso completado en " + (System.currentTimeMillis() - t) + " ms.");
	}
}
