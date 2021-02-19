package org.example.test.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class ConcurrencyTest {

	/**
	 * Main thread will block until completion	
	 */
	@Test
	public void parallelProcessing() throws Exception {
		List<String> outputScraper;
		CountDownLatch countDownLatch;
		
		outputScraper = Collections.synchronizedList(new ArrayList<>());
		countDownLatch = new CountDownLatch(5);
		
		List<Thread> workers = Stream
				.generate(() -> new Thread(new Worker(outputScraper, countDownLatch)))
				.limit(5)
				.collect(Collectors.toList());
		
		workers.forEach(Thread::start);
		countDownLatch.await();
		outputScraper.add("Latch released");

	}
	
}
