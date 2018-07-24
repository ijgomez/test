package org.example.test;

import java.util.Collection;
import java.util.LinkedList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MemoryWarningSystem.setPercentageUsageThreshold(0.6);

		MemoryWarningSystem mws = new MemoryWarningSystem();
		mws.addListener(new MemoryWarningSystem.Listener() {
			public void memoryUsageLow(long usedMemory, long maxMemory) {
				System.out.println("Memory usage low!!!");
				double percentageUsed = (((double) usedMemory) / maxMemory)*100;
				System.out.println("percentageUsed = " + percentageUsed + "%");
				MemoryWarningSystem.setPercentageUsageThreshold(0.8);
			}
		});

		Collection<Double> numbers = new LinkedList<Double>();
		while (true) {
			numbers.add(Math.random());
		}
	}

}
