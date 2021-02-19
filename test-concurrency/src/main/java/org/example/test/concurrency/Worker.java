package org.example.test.concurrency;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

	private List<String> outputScraper;

	private CountDownLatch countDownLatch;

	/**
	 * New Instance.
	 * 
	 * @param outputScraper
	 * @param countDownLatch
	 */
	public Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
		this.outputScraper = outputScraper;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		// TODO Do some work....
		outputScraper.add("Counted down");
		countDownLatch.countDown();
	}

}
