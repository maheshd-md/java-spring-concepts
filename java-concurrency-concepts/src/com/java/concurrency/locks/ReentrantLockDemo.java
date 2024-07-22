package com.java.concurrency.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

	/* 
 	 ReentrantLock: 
   		It is used to put the lock on shared resource so that any other thread cannot access it and unlock it once the operation is performed.
   	 Differece between synchronized block and ReentrantLock:
	 	Compared to the ReentrantLock, the synchronized keyword is more restrictive since it automatically locks and unlocks at the beginning and end of the scope, 
	 	while the ReentrantLock allows you to lock and unlock at any point in your code.
  	*/

	static Integer count = 0;
	static Lock lock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {

		Runnable r1 = () -> {
			for (int i = 0; i < 100000; i++) {
				// If current thread did not get the lock, the code is blocked here until the
				// lock is acquired.
				lock.lock();
				count++;
				lock.unlock();
			}
		};

		Runnable r2 = () -> {
			for (int i = 0; i < 100000; i++) {
				lock.lock();
				count--;
				lock.unlock();
			}
		};

		Thread incrementer = new Thread(r1, "Thread-1");
		Thread decrementer = new Thread(r2, "Thread-2");
		incrementer.start();
		decrementer.start();
		incrementer.join();
		decrementer.join();
		System.out.println("Count = " + count);
		
		
		
		// Lock.tryLock() demo
		Runnable r3 = () -> {

			Integer lockSuccessCount = 0;
			Integer lockFailCount = 0;

			for (int i = 0; i < 100000; i++) {
				/*
				 * If current thread got the lock, code in the if block will be executed. If
				 * current thread did not get the lock, code is not blocked, it will execute the
				 * code in the else block.
				 */
				if (lock.tryLock()) {
					lockSuccessCount++;
					lock.unlock();
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
