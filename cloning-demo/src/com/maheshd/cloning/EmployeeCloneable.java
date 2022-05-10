/**
 * 
 */
package com.maheshd.cloning;

import java.util.List;

/**
 * @author Mahesh
 *
 */
public class EmployeeCloneable implements Cloneable {
	
	private int eid;
	private String name;
	private long salary;
	private Address address;
	private List<String> skills;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		EmployeeCloneable cloned = (EmployeeCloneable) super.clone();
		cloned.eid = this.eid;
		cloned.name = this.name;
		cloned.salary = this.salary;
		
		// Deep copy
		Address clonedAddress = new Address();
		clonedAddress.setArea("Nanded");
		clonedAddress.setCity("Pune");
		clonedAddress.setPincode(411041);
		cloned.setAddress(clonedAddress);
		
		// Shallow copy
		cloned.skills = this.skills;
		
		return cloned;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
}
