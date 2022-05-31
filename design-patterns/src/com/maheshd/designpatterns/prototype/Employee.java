/**
 * 
 */
package com.maheshd.designpatterns.prototype;

/**
 * @author Mahesh
 *
 */
public abstract class Employee implements Cloneable{

	String type;
	
	public abstract void work();
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
