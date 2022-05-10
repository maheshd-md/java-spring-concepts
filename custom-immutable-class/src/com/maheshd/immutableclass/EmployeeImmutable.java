/**
 * 
 */
package com.maheshd.immutableclass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahesh
 *
 */
public final class EmployeeImmutable {

	private final Integer eid;
	private final String name;
	private final Integer salary;
	private final Address address;
	private final List<String> skills;
	
	public EmployeeImmutable(Integer eid, String name, Integer salary, Address address, List<String> skills) {
		super();
		this.eid = eid;
		this.name = name;
		this.salary = salary;
		this.address = new Address(address.getArea(), address.getCity(), address.getPincode());
		this.skills = new ArrayList<>(skills);
	}

	public Integer getEid() {
		return eid;
	}

	public String getName() {
		return name;
	}

	public Integer getSalary() {
		return salary;
	}

	public Address getAddress() {
		return address;
	}

	public List<String> getSkills() {
		return skills;
	}
}
