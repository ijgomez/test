package org.example.test;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		double freeMemory = (double) ((Runtime.getRuntime().freeMemory()) / (double)(1024 * 1024));
		double maxMemory = (double) ((Runtime.getRuntime().maxMemory()) / (double)(1024 * 1024));		
		double totalMemory = (double) ((Runtime.getRuntime().totalMemory()) / (double)(1024 * 1024));		

		System.out.println("MEMORIA. LIBRE: " + Runtime.getRuntime().freeMemory() + " bytes. ASIGNADA: " + Runtime.getRuntime().totalMemory() + " bytes. MAX: " + Runtime.getRuntime().maxMemory() + " bytes. ");		
		System.out.println("MEMORIA. LIBRE: " + freeMemory + " MB. ASIGNADA: " + totalMemory + " MB. MAX: " + maxMemory + " MB.");
	
	}

}
