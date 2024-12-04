package com.java.concurrency.threadlocal;

public class ThreadLocalDemo {

	/*
	 * ThreadLocal ensures that every thread accessing same resource, has it's own copy of it.
	 * In this case, both the threads t1 and t2 have their own copy of the count
	 */
	static ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);
	
	public static void main(String[] args) throws InterruptedException {
		
		Runnable r1 = () -> {
			for(int i=0; i<10; i++) {
				// get() method returns the current thread's copy of count
				Integer threadIndividualCount = count.get();
				count.set(++threadIndividualCount);
			}
			System.out.println(Thread.currentThread().getName() + ": count = " +count.get());
		};
		
		Runnable r2 = () -> {
			for(int i=0; i<10; i++) {
				Integer threadIndividualCount = count.get();
				count.set(++threadIndividualCount);
			}
			System.out.println(Thread.currentThread().getName() + ": count = " +count.get());
		};
		
		Thread t1 = new Thread(r1, "Thread-1");
		Thread t2 = new Thread(r2, "Thread-2");
		t1.start();
		t2.start();
		
	}
}
