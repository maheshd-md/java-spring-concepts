/**
 * 
 */
package com.maheshd.executerservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Mahesh
 *
 */
public class MainClass {

	public static void main(String[] args) {

		System.out.println("1. Fixed Thread Pool Executor Service: ");
		/*
		 * Fixed number of threads will be created. In this example, 5 threads will
		 * execute 10 tasks.
		 */
		ExecutorService fixedThreadPoolExecutorService = Executors.newFixedThreadPool(5);
		for (int i = 1; i <= 10; i++) {
			Task task = new Task();
			fixedThreadPoolExecutorService.execute(task);
		}

		printSeparatorAfterExecutorCompletion(fixedThreadPoolExecutorService);

		/*
		 * ---Other methods of ExecutorService:
		 * 1. executorService.shutdown() - initiate shutdown 
		 * 2. executorService.isShutdown() - returns true if executorService is shutdown 
		 * 3. executorService.isTerminated() - returns true if all the tasks are completed 
		 * 4. executorService.awaitTermination(long timout, TimeUnit timeUnit) - blocks current thread execution 
		 * until timout or termiation of executorService
		 */

		System.out.println("2. Cached Thread Pool Executor Service: ");
		/*
		 * Number of threads is not fixed, new thread will be created if all the
		 * previously created threads are busy.
		 */
		ExecutorService cachedThreadPoolExecutorService = Executors.newCachedThreadPool();
		for (int i = 1; i <= 10; i++) {
			Task task = new Task();
			cachedThreadPoolExecutorService.execute(task);
		}

		printSeparatorAfterExecutorCompletion(cachedThreadPoolExecutorService);

		System.out.println("3. Scheduled Thread Pool Executor Service: ");
		/*
		 * Fixed number of threads will be created. Note the type is
		 * ScheduledExecutorService which extends ExecutorService
		 */
		ScheduledExecutorService scheduledThreadPoolExecutorService = Executors.newScheduledThreadPool(5);
		for (int i = 1; i <= 10; i++) {
			Task task = new Task();
			/*
			 * ScheduledExecutorService is scheduled to execute the tasks after 5 seconds.
			 * After 5 seconds, it will start executing all the tasks simultaneously using
			 * maximum 5 threads
			 */
			scheduledThreadPoolExecutorService.schedule(task, 5, TimeUnit.SECONDS);

			/*
			 * ScheduledExecutorService will execute repeatedly after initial delay of 5
			 * seconds, and after that it will execute every 10 seconds period until it's
			 * termination (until we call shutdown() method)
			 */
			long initialDelay = 5;
			long period = 10;
			scheduledThreadPoolExecutorService.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);

			/*
			 * ScheduledExecutorService will execute repeatedly after initial delay of 5
			 * seconds, and it will delay 10 seconds after completion of tasks for next
			 * repeated execution until it's termination (until we call shutdown() method)
			 */
			long delay = 10;
			scheduledThreadPoolExecutorService.scheduleWithFixedDelay(task, initialDelay, delay, TimeUnit.SECONDS);
		}

		printSeparatorAfterExecutorCompletion(scheduledThreadPoolExecutorService);

		System.out.println("4. Single Thread Pool Executor Service: ");
		// Single thread will execute all the 10 tasks
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 1; i <= 10; i++) {
			Task task = new Task();
			singleThreadExecutor.execute(task);
		}

		printSeparatorAfterExecutorCompletion(singleThreadExecutor);
	}

	private static void printSeparatorAfterExecutorCompletion(ExecutorService executorService) {
		try {
			/*
			 * After shutdown(), no new task will be accepted, if new task is assigned, it
			 * will throw RejctionExecutionException
			 */
			executorService.shutdown();

			/*
			 * To block current (main) thread execution for 24 hours or until termination of
			 * executor service
			 */
			executorService.awaitTermination(24l, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Print separator line after completion of executor service
		System.out.println("----------------------------------------------------------------------");
	}
}
