package com.java.concurrency.producer_consumer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumerUsingSynchronized {

	public static void main(String[] args) {

		Queue<Integer> queue = new ArrayDeque<Integer>();

		Runnable producer = () -> {
			while (true) {
				synchronized (queue) {
					System.out.println(queue);
					if (3 == queue.size()) {
						System.out.println("Full..." + Thread.currentThread().getName() + " is waiting");
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						int num = new Random().nextInt(10);
						queue.add(num);
						queue.notifyAll();
					}
				}
			}
		};

		Runnable consumer = () -> {
			while (true) {
				synchronized (queue) {
					System.out.println(queue);
					if (queue.isEmpty()) {
						System.out.println("Empty..." + Thread.currentThread().getName() + " is waiting");
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						queue.poll();
						queue.notifyAll();
					}
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
