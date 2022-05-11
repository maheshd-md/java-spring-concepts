package java8features;

import java.util.Objects;

public class Employee implements Comparable<Employee> {

	String name;
	Integer salary;
	String skill;
	
	public Employee(String name, Integer salary, String skill) {
		super();
		this.name = name;
		this.salary = salary;
		this.skill = skill;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(name, other.name);
	}

	
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
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
		return "Employee [name=" + name + ", salary=" + salary + ", skill=" + skill + "]";
	}

	@Override
	public int compareTo(Employee o) {
		return this.getSalary().compareTo(o.getSalary());
	}
}
