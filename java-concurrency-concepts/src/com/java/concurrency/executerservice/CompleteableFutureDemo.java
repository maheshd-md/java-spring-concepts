package com.java.concurrency.executerservice;

import java.util.concurrent.CompletableFuture;

public class CompleteableFutureDemo {


	static int orderCount = 0;
	
	public static void main(String[] args) {
		
		
		for(int i=0; i<10; i++) {
			CompleteableFutureDemo demo = new CompleteableFutureDemo();
			CompletableFuture.supplyAsync(() -> demo.getOrder())
								.thenApply(order -> demo.createOrder(order))
								.thenApply(order -> demo.payment(order))
								.thenApply(order -> demo.completeOrder(order));
		}
	}
	
	public Order getOrder() {
		orderCount++;
		Order order = new Order();
		order.orderName = "order-" +orderCount;
		return order;
	}
	
	public Order createOrder(Order order) {
		order.orderStatus = "CREATED";
		System.out.println(order.orderName + " created!");
		return order;
	}
	
	public Order payment(Order order) {
		order.orderStatus = "CREATED";
		System.out.println(order.orderName + " paid!");
		return order;
	}
	
	public Order completeOrder(Order order) {
		order.orderStatus = "COMPLETED";
		System.out.println(order.orderName + " completed!");
		return order;
	}
}

class Order {
	
	String orderName;
	String orderStatus;
	
}
