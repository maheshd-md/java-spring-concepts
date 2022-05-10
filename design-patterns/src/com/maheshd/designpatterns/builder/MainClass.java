/**
 * 
 */
package com.maheshd.designpatterns.builder;

/**
 * @author Mahesh
 *
 */
public class MainClass {

	public static void main(String[] args) {
		
		Employee employee = new Employee().builder()
							.eid(1)
							.name("Mahesh")
							.salary(100000)
							.build();
		
		System.out.println(employee);
	}
}
