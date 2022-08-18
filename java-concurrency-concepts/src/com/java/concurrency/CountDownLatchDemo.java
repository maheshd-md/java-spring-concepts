package com.java.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {

		/*
		 * CountDownLatch allows one or more threads to wait until a set of
		 * operations being performed in other threads completes
		 */
		CountDownLatch latch = new CountDownLatch(2);
		AtomicInteger count = new AtomicInteger(0);

		Increamenter increamenter = new Increamenter(latch, count, "Increamenter 1");
		Decreamenter decreamenter = new Decreamenter(latch, count, "Decreamenter 2");

		increamenter.start();
		decreamenter.start();

		// Main thread waits for completion of incrementer and decremnter threads
		latch.await();

		System.out.println("Count = " + count);
		System.out.println("Main thread completed!");

	}

	static class Increamenter extends Thread {

		CountDownLatch latch;
		AtomicInteger count;

		public Increamenter(CountDownLatch latch, AtomicInteger count, String name) {
			super();
			this.latch = latch;
			this.count = count;
			this.setName(name);
		}

		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				this.count.incrementAndGet();
			}
			System.out.println(this.getName() + " completed!");
			latch.countDown();
		}
	}

	static class Decreamenter extends Thread {

		CountDownLatch latch;
		AtomicInteger count;

		public Decreamenter(CountDownLatch latch, AtomicInteger count, String name) {
			super();
			this.latch = latch;
			this.count = count;
			this.setName(name);
		}

		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				this.count.decrementAndGet();
			}
			System.out.println(this.getName() + " completed!");
			latch.countDown();
		}
	}

}
