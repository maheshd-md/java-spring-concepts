package com.java.concurrency.executerservice;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FibonacciRecursiveTask extends RecursiveTask<Integer> {

	Integer number;
	
	
	public FibonacciRecursiveTask(Integer n) {
		super();
		this.number = n;
	}


	@Override
    protected Integer compute() {
		Integer n = number;
        if (n <= 10) {
            number = fib(number);
        } else {
        	FibonacciRecursiveTask f1 = new FibonacciRecursiveTask(n - 1);
        	FibonacciRecursiveTask f2 = new FibonacciRecursiveTask(n - 2);
            ForkJoinTask.invokeAll(f1, f2);
            number = f1.number + f2.number;
        }
        return number;
    }

    private static Integer fib(Integer n) {
        if (n <= 1) return n;
        else return fib(n - 1) + fib(n - 2);
    }

}
