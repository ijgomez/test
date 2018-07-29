package org.example.test;

import org.apache.log4j.Logger;

public class Main {
	
	
	private static Logger LOGGER = Logger.getLogger(Main.class);
	
	/** Tiempo maximo en segundos que la aplicacion puede estar en ejecucion. */
	private static int deathwatchTime = 1800;

	public static void main(String[] args) {
		try {
			ApplicationService applicationService;
			
			
			applicationService = new ApplicationService();
			
			ControlThread controlThread = new Main.ControlThread();
			controlThread.setName("DWATCH");
			controlThread.start();
			
			Thread.sleep(1000);
			applicationService.execute();
			
			// FIN DE EJECUCION **************************************************************************************************
			

			if (LOGGER.isInfoEnabled()) LOGGER.info("Parando timer de control.");
			controlThread.parar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
public static class ControlThread extends Thread {
		
		public void run() {
			try {
				if (LOGGER.isInfoEnabled()) { LOGGER.info("Deathwatch is running... The Process will be alive for " + deathwatchTime + " seconds."); }
				
				synchronized (this) {
					this.wait(deathwatchTime * 1000);
				}

				int active = Thread.activeCount();
				
				if (LOGGER.isInfoEnabled()) { LOGGER.info("Threads activos finalizados: " + active); }
			    Thread all[] = new Thread[active];
			    Thread.enumerate(all);
			    for (int i = 0; i < active; i++) {
			    	if (all[i] != null && !all[i].equals(this)) {
			    		if (LOGGER.isDebugEnabled()) { LOGGER.debug(" Tread activo: " + all[i].getName()); }
			    		all[i].interrupt();
			    	}
			    }
			
			    // Por si es necesario, se invoca el flush de los ficheros de log por si es necesario volcar alguno.
			    // FicheroLog.flushLogs();
			    
			    if (LOGGER.isInfoEnabled()) { LOGGER.info("Programa finalizado."); }
				System.exit(0);
			} catch (InterruptedException e) {
				LOGGER.error("Deathwatch fail. Operation not will completed. Program exit now.", e);
			}
		}
		
		public void parar() {
			synchronized (this) {
				this.notify();
			}
		}
		
	}
}
