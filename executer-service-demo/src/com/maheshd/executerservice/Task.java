/**
 * 
 */
package com.maheshd.executerservice;

/**
 * @author Mahesh
 *
 */
public class Task implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}

}
