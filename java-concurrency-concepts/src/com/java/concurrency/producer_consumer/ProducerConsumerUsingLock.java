package com.java.concurrency.producer_consumer;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerUsingLock {

	public static void main(String[] args) {

		/*
		 * ArrayBlockingQueue is by default synchronized. It is initialized with
		 * capacity of 3. It can store max 3 elements.
		 */
		Queue<Integer> queue = new ArrayDeque<Integer>(3);
		Lock lock = new ReentrantLock();

		Runnable producer = () -> {
			while (true) {
				lock.lock();
				System.out.println(queue);
				if(queue.size() == 3) {
					System.out.println("Queue is full...!");
				} else {
					queue.add(new Random().nextInt(10));
				}
				lock.unlock();
			}
		};

		Runnable consumer = () -> {
			while (true) {
				lock.lock();
				System.out.println(queue);
				if(0 == queue.size()) {
					System.out.println("Queue is empty...!");					
				} else {
					queue.remove();
				}
				lock.unlock();
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
