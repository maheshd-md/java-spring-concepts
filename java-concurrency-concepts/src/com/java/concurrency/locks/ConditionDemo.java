package com.java.concurrency.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

	static Lock lock = new ReentrantLock();
	static Condition condition = lock.newCondition();

	public static void main(String[] args) throws InterruptedException {

		/*
		 * condition.await() and condition.signal() are similar to object.wait() and
		 * object.notify(). The wait/notify methods are called inside synchronized block
		 * and await/signal methods are called after acquiring the lock.
		 * 
		 * condition.singalAll() method will signal all the methods on that condition,
		 * signal() method will signal only the first method waiting on that condition.
		 * In this way fairness is maintained.
		 */

		Runnable r1 = () -> {
			lock.lock(); // lock acquired
			System.out.println("Lock acquired by Thread-1");
			try {
				System.out.println("Awaiting for signal");
				// Releases lock and wait for signal
				condition.await();
				// After getting signal and acquire lock again and start execution from this
				// point
				System.out.println("After signal");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println("Thread-1 releasing lock");
				lock.unlock();
			}
		};

		Runnable r2 = () -> {
			lock.lock();
			System.out.println("Lock acquired by Thread-2");
			System.out.println("Singalling waiting thread");
			condition.signal();
			// condition.signalAll();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread-2 releasing lock");
			lock.unlock();
		};

		Thread incrementer = new Thread(r1, "Thread-1");
		Thread decrementer = new Thread(r2, "Thread-2");
		incrementer.start();
		Thread.sleep(1000);
		decrementer.start();
		incrementer.join();
		decrementer.join();

	}
}
