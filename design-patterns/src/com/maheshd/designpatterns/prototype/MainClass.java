/**
 * 
 */
package com.maheshd.designpatterns.prototype;

/**
 * @author Mahesh
 *
 */
public class MainClass {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		
		Admin admin1 = (Admin) PrototypeRegistry.getEmployee("admin");
		Admin admin2 = (Admin) PrototypeRegistry.getEmployee("admin");
		
		System.out.println(admin1 == admin2);
	}
}
