package org.example.test.pool;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ThreadPoolManager {

	private static Logger LOGGER = Logger.getLogger(ThreadPoolManager.class);

	private List<Thread> threads = new ArrayList<>();

	private final int capacity;

	private ThreadPoolQueue queue = new ThreadPoolQueue(9999);

	private boolean isStopped = false;

	/** Numero de hilos activos dentro de la clase. */
	private int hilosActivos = 0;

	/** Numero de hilos lanzados dentro de la clase. */
	private int hilosLanzados = 0;

	public ThreadPoolManager(int numberOfThreads) {
		this.capacity = numberOfThreads;
	}

	public void start() {
		for (int i = 0; i < capacity; i++) {
			Thread thread = new Thread(new ThreadPoolWorker(queue) {

				public void register() {
					doRegister();
				}

				public void unRegister() {
					doUnRegister();
				}

				public boolean doStop() {
					return isStopped;
				}
			}, "theadpool-" + (i + 1));
			threads.add(thread);
			thread.start();
			hilosLanzados++;
		}
	}

	public void submit(Runnable r) throws InterruptedException {
		queue.enqueue(r);
		LOGGER.trace("S. Task Activas: " + hilosActivos + " Hilos Arrancado: " + hilosLanzados + " Max Hilos: " + capacity + " Queue: " + queue.size());
	}

	protected void doRegister() {
		synchronized (this) {
			hilosActivos++;
			LOGGER.trace("R. Task Activas: " + hilosActivos + " Hilos Arrancado: " + hilosLanzados + " Max Hilos: " + capacity + " Queue: " + queue.size());
		}
	}

	protected void doUnRegister() {
		synchronized (this) {
			hilosActivos--;
			LOGGER.trace("U. Task Activas: " + hilosActivos + " Hilos Arrancado: " + hilosLanzados + " Max Hilos: " + capacity + " Queue: " + queue.size());
			if (hilosActivos == 0 && queue.size() == 0) {
				this.notify();
				LOGGER.trace("U. Continuando aplicacion...");
			}
		}
	}

	public void waitAndStop() throws InterruptedException {
		synchronized (this) {
			if (hilosActivos > 0) {
				LOGGER.trace("W. Task Activas: " + hilosActivos + " Hilos Arrancado: " + hilosLanzados + " Max Hilos: " + capacity + " Queue: " + queue.size());
				this.wait();
			}
		}
		this.stop();
	}

	public void stop() {
		LOGGER.debug("Stopping TheadPoolManager....");
		this.isStopped = true;
		queue.close();
		for (int i = 0; i < threads.size(); i++) {
			Thread t = (Thread) threads.get(i);
			t.interrupt();
		}
		threads.clear();
	}
}
