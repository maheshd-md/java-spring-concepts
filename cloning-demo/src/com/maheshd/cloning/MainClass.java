/**
 * 
 */
package com.maheshd.cloning;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahesh
 *
 */
public class MainClass {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		EmployeeCloneable employee = new EmployeeCloneable();
		employee.setEid(1);
		employee.setName("Mahesh");
		employee.setSalary(100000);
		Address address = new Address();
		address.setArea("Nanded");
		address.setCity("Pune");
		address.setPincode(411041);
		employee.setAddress(address);
		List<String> skills = new ArrayList<>();
		skills.add("Java");
		skills.add("Spring Boot");
		employee.setSkills(skills);
		
		EmployeeCloneable cloned = (EmployeeCloneable) employee.clone();
		
		System.out.println(employee == cloned);
		// Check deep copy
		System.out.println(employee.getAddress() == cloned.getAddress());
		// Check shallow copy
		System.out.println(employee.getSkills().hashCode() == cloned.getSkills().hashCode());
	}
}
