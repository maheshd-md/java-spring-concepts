package com.java.concurrency.exceptionhandling;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandlingInMultithreadingDemo {

	public static void main(String[] args) {

		MyTask task = new MyTask();

		MyTaskExceptionHandler myTaskExceptionHandler = new MyTaskExceptionHandler();

		task.setUncaughtExceptionHandler(myTaskExceptionHandler);
		task.start();
	}
}

class MyTask extends Thread {

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Throw Runtime exception
		throw new RuntimeException();
	}
}

class MyTaskExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Uncaught exception handled!!!");
	}

}