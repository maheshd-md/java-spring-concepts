package com.java.concurrency.locks;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

	/*
	 * Here AtomicInteger is used. Because the value of normal Integer is updated in
	 * 3 steps: 
	 * 1. Fetch the value from memory 
	 * 2. Increment by 1 
	 * 3. Write the value in the memory. 
	 * This can cause inconsistent value behavior if the Integer object is accessed by multiple threads.
	 * 
	 * The AtomicInteger ensures that value is updated in single operation.
	 * 
	 * The volatile keyword can be used for atomic objects. volatile keyword ensures that 
	 * the value is read from the thread shared cache and not from the thread local cache.
	 */
	static volatile AtomicInteger count = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {

		Runnable r1 = () -> {
			for (int i = 0; i < 100000; i++) {
				count.incrementAndGet();
			}
		};

		Runnable r2 = () -> {
			for (int i = 0; i < 100000; i++) {
				count.decrementAndGet();
			}
		};

		Thread incrementer = new Thread(r1, "Thread-1");
		Thread decrementer = new Thread(r2, "Thread-2");

		incrementer.start();
		decrementer.start();

		/*
		 *  Calling join() method on incrementer and decrementer threads
		 *  will blocks the current thread (main thread in this case) 
		 *  until incrementer and decrementer threads are completed
		 */
		incrementer.join();
		decrementer.join();

		System.out.println("Count = " + count);
	}
}
