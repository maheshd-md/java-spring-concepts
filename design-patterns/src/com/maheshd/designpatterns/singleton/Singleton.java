/**
 * 
 */
package com.maheshd.designpatterns.singleton;

import java.io.Serializable;

/**
 * @author Mahesh
 *
 */
public class Singleton implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	
	// Lazy initialization
	private static Singleton instance;
	
	// Private constructor
	private Singleton() {
		// Avoid creating object using reflection
		if(null != instance) {
			throw new InstantiationError("Object creation not allowed");
		}
	}
	
	public static Singleton getInstance() {
		if (null == instance) {
			synchronized (Singleton.class) {
				// Thread safe
				if (null == instance) {
					// Double checking because object might be half initialized during first check
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	// Avoid creating object using de-serialization
	protected Object readResolve() {
		return instance;
	}
	
	// Avoid creating object using clone method
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
