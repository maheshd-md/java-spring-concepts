/**
 * 
 */
package com.maheshd.designpatterns.singleton;

/**
 * @author Mahesh
 *
 */
public class Singleton {

	// Lazy initialization
	private static Singleton instance;
	
	private Singleton() {
		// Private constructor
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
}
