package com.java.concurrency.locks;

public class SynchronizedDemo {

	static class Counter {

		Integer count = 0;

		Counter(Integer count) {
			this.count = count;
		}
	}

	/*
	 * Here Integer object is not used as shared resource, instead, the class
	 * Counter is created and it's object is used as shared resource and used in
	 * synchronized keyword. Integer is value based type, so it's object cannot be
	 * used for synchronization.
	 */
	static Counter counter = new Counter(0);

	public static void main(String[] args) throws InterruptedException {

		Runnable r1 = () -> {
			for (int i = 0; i < 100000; i++) {
				synchronized (counter) {
					counter.count++;
				}
			}
		};

		Runnable r2 = () -> {
			for (int i = 0; i < 100000; i++) {
				synchronized (counter) {
					counter.count--;
				}
			}
		};

		Thread incrementer = new Thread(r1, "Thread-1");
		Thread decrementer = new Thread(r2, "Thread-2");
		incrementer.start();
		decrementer.start();
		incrementer.join();
		decrementer.join();
		System.out.println("Count = " + counter.count);
	}
}
