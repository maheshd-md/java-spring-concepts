/**
 * 
 */
package com.maheshd.designpatterns.factory;

/**
 * @author Mahesh
 *
 */
public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Pen pen1 = PenFactory.getPen("black");
		pen1.write();
		
		Pen pen2 = PenFactory.getPen("blue");
		pen2.write();
	}

}
