package org.example.test.pool;

import java.util.LinkedList;
import java.util.List;

public class ThreadPoolQueue {

	private List<Object> queue = new LinkedList<>();

	private int limit = 999;

	private boolean closed = false;

	public ThreadPoolQueue(int limit) {
		this.limit = limit;
	}

	public synchronized void enqueue(Object item) throws InterruptedException {

		if (closed) {
			throw new RuntimeException();
		}

		while (this.queue.size() == this.limit) {
			wait();
		}
		if (this.queue.size() == 0) {
			notifyAll();
		}
		this.queue.add(item);
	}

	public synchronized Object dequeue() throws InterruptedException {

		while (!closed && this.queue.size() == 0) {
			wait();
		}
		if (this.queue.size() == this.limit) {
			notifyAll();
		}
		if (queue.size() == 0) {
			return null;
		}
		return this.queue.remove(0);
	}

	public synchronized int size() {
		return queue.size();
	}

	public synchronized void close() {
		closed = true;
		notifyAll();
	}

	public synchronized void open() {
		closed = false;
	}
}
