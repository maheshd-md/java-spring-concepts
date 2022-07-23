package com.java.concurrency.executerservice;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {

	private Integer input;
	
	public CallableTask(Integer input) {
		super();
		this.input = input;
	}

	@Override
	public Integer call() throws Exception {
		Integer sum = 0;
		for(int i=1; i<=input; i++) {
			sum += i;
		}
		return sum;
	}
}
