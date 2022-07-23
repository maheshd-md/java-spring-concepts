package com.java.concurrency.producer_consumer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerUsingCondition {

	public static void main(String[] args) {

		Queue<Integer> queue = new ArrayDeque<Integer>(3);
		Lock lock = new ReentrantLock();
		Condition isAdded = lock.newCondition();
		Condition isRemoved = lock.newCondition();

		Runnable producer = () -> {
			try {
				while (true) {
					lock.lock();
					System.out.println(queue);
					while (queue.size() == 3) {
						System.out.println("Queue is full...!");
						isRemoved.await();
					}
					queue.add(new Random().nextInt(10));
					isAdded.signal();
					lock.unlock();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Runnable consumer = () -> {
			try {
				while (true) {
					lock.lock();
					System.out.println(queue);
					while (0 == queue.size()) {
						System.out.println("Queue is empty...!");
						isAdded.await();
					}
					queue.remove();
					isRemoved.signal();
					lock.unlock();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Thread producer1 = new Thread(producer, "producer-1");
		Thread producer2 = new Thread(producer, "producer-2");
		Thread consumer1 = new Thread(consumer, "consumer-1");
		Thread consumer2 = new Thread(consumer, "consumer-2");

		producer1.start();
		producer2.start();

		consumer1.start();
		consumer2.start();

	}

}
