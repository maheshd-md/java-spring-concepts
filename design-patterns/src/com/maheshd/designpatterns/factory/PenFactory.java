/**
 * 
 */
package com.maheshd.designpatterns.factory;

/**
 * @author Mahesh
 *
 */
public class PenFactory {
	
	public static Pen getPen(String color) {
		if("blue".equals(color)) {
			return new BluePen();
		} else if("black".equals(color)) {
			return new BlackPen();
		}
		return null;
	}

}
