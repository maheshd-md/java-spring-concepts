/**
 * 
 */
package com.maheshd.designpatterns.builder;

/**
 * @author Mahesh
 *
 */
public class Employee {

	private Integer eid;
	private String name;
	private Integer salary;
	
	public EmployeeBuilder builder() {
		return new EmployeeBuilder();
	}
	
	class EmployeeBuilder {
		
		private Integer eid;
		private String name;
		private Integer salary;
		
		public EmployeeBuilder eid(Integer eid) {
			this.eid = eid;
			return this;
		}
		
		public EmployeeBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public EmployeeBuilder salary(Integer salary) {
			this.salary = salary;
			return this;
		}
		
		public Employee build() {
			Employee.this.eid = this.eid;
			Employee.this.name = this.name;
			Employee.this.salary = this.salary;
			return Employee.this;
		}
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", name=" + name + ", salary=" + salary + "]";
	}
}
