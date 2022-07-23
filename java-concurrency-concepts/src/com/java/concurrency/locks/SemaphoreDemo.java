package com.java.concurrency.locks;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	static Integer count = 0;
	// The constructor parameter "permit" defines the number of threads allowed to acquire the lock
	static Semaphore lock = new Semaphore(5);

	public static void main(String[] args) throws InterruptedException {

		Runnable r1 = () -> {
			for (int i = 0; i < 100000; i++) {
				// If current thread did not get the lock, the code is blocked here until the
				// lock is acquired.
				try {
					lock.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count++;
				lock.release();
			}
		};

		Runnable r2 = () -> {
			for (int i = 0; i < 100000; i++) {
				lock.acquireUninterruptibly(2);
				count--;
				lock.release(2);
			}
		};

		Thread incrementer = new Thread(r1, "Thread-1");
		Thread decrementer = new Thread(r2, "Thread-2");
		incrementer.start();
		decrementer.start();
		incrementer.join();
		decrementer.join();
		System.out.println("Count = " + count);
		
		// Lock.tryAcquire() demo
		Runnable r3 = () -> {

			Integer lockSuccessCount = 0;
			Integer lockFailCount = 0;

			for (int i = 0; i < 100000; i++) {
				/*
				 * If current thread got the lock, code in the if block will be executed. If
				 * current thread did not get the lock, code is not blocked, it will execute the
				 * code in the else block.
				 */
				if (lock.tryAcquire()) {
					lockSuccessCount++;
					lock.release();
				} else {
					lockFailCount++;
				}
			}
			System.out.println(Thread.currentThread().getName() + " got the lock " + lockSuccessCount + " times");
			System.out.println(Thread.currentThread().getName() + " did not get the lock " + lockFailCount + " times");
		};
		
		Thread t3 = new Thread(r3, "Thread-3");
		Thread t4 = new Thread(r3, "Thread-4");
		t3.start();
		t4.start();
		t3.join();
		t4.join();
	}
}
