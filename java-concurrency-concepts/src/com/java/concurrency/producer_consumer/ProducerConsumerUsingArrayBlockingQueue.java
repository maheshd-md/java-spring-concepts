package com.java.concurrency.producer_consumer;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerUsingArrayBlockingQueue {

	public static void main(String[] args) {

		/*
		 * ArrayBlockingQueue is by default synchronized.
		 * It is initialized with capacity of 3. It can store max 3 elements.
		 */
		Queue<Integer> queue = new ArrayBlockingQueue<>(3);

		Runnable producer = () -> {
			while (true) {
				try {
					// If queue is full add() method throws IllegalStateException
					queue.add(new Random().nextInt(10));
					System.out.println(queue);
				} catch (IllegalStateException e) {
					System.out.println("Queue is full...!");
				}
			}
		};

		Runnable consumer = () -> {
			while (true) {
				try {
					/*
					 *  If queue is empty, remove() method throws NoSuchElementException.
					 *  There is poll() method also, which does not throw any exception if queue is empty.
					 */
					queue.remove();
					System.out.println(queue);
				} catch (NoSuchElementException e) {
					System.out.println("Queue is empty...!");
				}
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
