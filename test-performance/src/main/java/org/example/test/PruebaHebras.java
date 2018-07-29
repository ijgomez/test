package org.example.test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PruebaHebras {

	public static class MiHebra implements Callable {
		
		private int dato;
		
		private String msg;
		
		public MiHebra(String msg) {
			this.msg = msg;
		}
		
		@Override
		public Object call() {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + " - " + msg);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {}
			}
			dato = new Random().nextInt();
			
			return dato;
		}
		
		public int getDato() {
			return dato;
		}
	}
	
	public static void main(String[] args) throws Exception {
		Runtime r = Runtime.getRuntime();
		
		System.out.println("Procesadores = " + r.availableProcessors());
		
		MiHebra hebra1 = new MiHebra("Hebra 1");
		MiHebra hebra2 = new MiHebra("Hebra 2");
		
		//new Thread(hebra1).start();
		
		//ExecutorService svc = Executors.newFixedThreadPool(10);
		ExecutorService svc = Executors.newCachedThreadPool();
		Future<?>[] resultados = new Future<?>[20];
		for (int i = 0; i < 20; i++) {
			MiHebra h = new MiHebra("Hebra " + i);
			resultados[i] = (Future<MiHebra>)svc.submit(h);
			
			Thread.sleep(1000);
		}
		System.out.println("Tareas Enviadas");
		
		for (int i = 0; i < resultados.length; i++) {
			Future<MiHebra> futuro = (Future<MiHebra>)resultados[i];
//			if (futuro.isDone())
			try {
				MiHebra h = futuro.get();
				System.out.println("Resultado de " + h.msg + " = " + h.getDato());
			} catch (Exception e) {e.printStackTrace();}
			
		}
		svc.shutdown();
	}

}
