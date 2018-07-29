package org.example.test.pool;

import org.apache.log4j.Logger;

public abstract class ThreadPoolWorker implements Runnable {
	
	private static Logger LOGGER = Logger.getLogger(ThreadPoolWorker.class);

	private ThreadPoolQueue taskQueue = null;

	public ThreadPoolWorker(ThreadPoolQueue myQueue){
        this.taskQueue = myQueue;
    }

    public void run(){
    	while(true){
            Runnable r;
			try {
				if (doStop()) {
	            	break;
	            }
				r = (Runnable) taskQueue.dequeue();
				 if (r == null || doStop()) {
					 break;
				 }
				register();
	            r.run();
	            unRegister();
	            if (doStop()) {
	            	break;
	            }
			} catch (InterruptedException e) {
				LOGGER.error("Fallo al realizar el trabajo", e);
			}
           
        }
    }

    public abstract void register();
    
    public abstract void unRegister();

    public abstract boolean doStop();
}
