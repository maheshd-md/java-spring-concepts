package com.java.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		
		CyclicBarrier barrier = new CyclicBarrier(3);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(new MyTask(barrier));
		executor.submit(new MyTask(barrier));
		executor.submit(new MyTask(barrier));
		
		
	}

	static class MyTask extends Thread {

		CyclicBarrier barrier;

		public MyTask(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			while (true) {
				try {
					System.out.println(Thread.currentThread().getName() + " cycle started!");
					System.out.println(Thread.currentThread().getName() + " cycle processing!");
					System.out.println(Thread.currentThread().getName() + " cycle ended!");

					/*
					 * Start new cycle only when all other threads reach at the same level i.e. when
					 * they call barrier.wait()
					 */
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
