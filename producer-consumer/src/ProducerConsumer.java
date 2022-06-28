import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumer {

	public static void main(String[] args) {
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		Object lock = new Object();
		
		Runnable producer = () -> {
			while(true) {
					try {
						synchronized (lock) {
							while(3 == queue.size()) {
								System.out.println("Full..." +Thread.currentThread().getName() + " is waiting");
								lock.wait();
							}
							int num = new Random().nextInt();
							queue.add(num);
							System.out.println(num + " produced by " +Thread.currentThread().getName());
							System.out.println(queue);
							lock.notifyAll();
						}
						Thread.sleep(1000 * new Random().nextLong(5));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		};
		
		Runnable consumer = () -> {
			while(true) {
					try {
						synchronized (lock) {
							while(queue.isEmpty()) {
								System.out.println("Empty..." +Thread.currentThread().getName() + " is waiting");
								lock.wait();
							}
							int num = queue.poll();
							System.out.println(num + " consumed by " +Thread.currentThread().getName());
							System.out.println(queue);
							lock.notifyAll();
						}
						Thread.sleep(1000 * new Random().nextLong(5));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
