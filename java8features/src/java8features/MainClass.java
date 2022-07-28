package java8features;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MainClass {

	public static void main(String[] args) {
		java8FeaturesDemo();
	}

	private static void java8FeaturesDemo() {
		List<Employee> list = new ArrayList<>();

		list.add(new Employee("Mahesh", 100000, "Java"));
		list.add(new Employee("Saurabh", 200000, "Java"));
		list.add(new Employee("Akshay", 200000, "Salesforce"));
		list.add(new Employee("Guru", 50000, "Java"));
		list.add(new Employee("Guru", 50000, "Java"));

		// Get salaries of all employees
		List<Integer> salaries = list.stream().map(Employee::getSalary).collect(Collectors.toList());
		System.out.println(salaries.toString());
		System.out.println("----------------------------------------------------------------------");

		// Sort employees by salary
		// Using Comparable - it works only if Employee implements Comparable
		list.stream().sorted().forEach(System.out::println);
		System.out.println("----------------------------------------------------------------------");
		// Using Comparator
		list.stream().sorted(Comparator.comparing(Employee::getSalary)).forEach(System.out::println);
		System.out.println("----------------------------------------------------------------------");
		Comparator<Employee> c = (e1, e2) -> {
			return e1.getSalary().compareTo(e2.getSalary());
		};
		list.stream().sorted(c).forEach(System.out::println);
		System.out.println("----------------------------------------------------------------------");

		// Sort employees by salary in reverse order
		// Using Comparable - it works only if Employee implements Comparable
		list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		System.out.println("----------------------------------------------------------------------");
		// Using Comparator
		list.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).forEach(System.out::println);

		// Print the distinct salaries
		// For the method distict(), hashCode & equals methods should be implemented by
		// the class
		list.stream().map(Employee::getSalary).distinct().forEach(System.out::println);
		System.out.println("----------------------------------------------------------------------");

		// Print the names of employees with salary >= 100000
		list.stream().filter(e -> {
			return e.getSalary() >= 100000;
		}).map(Employee::getName).forEach(System.out::println);
		System.out.println("----------------------------------------------------------------------");

		// Findout whether any employee has salary < 100000
		boolean result = list.stream().anyMatch(e -> {
			return e.getSalary() < 100000;
		});
		System.out.println(result);
		System.out.println("----------------------------------------------------------------------");

		// Findout whether all employees have salary >= 100000
		result = list.stream().allMatch(e -> {
			return e.getSalary() >= 100000;
		});
		System.out.println(result);
		System.out.println("----------------------------------------------------------------------");

		// Find the max salary
		Optional<Employee> emp = list.stream().max(Comparator.comparingInt(Employee::getSalary));
		System.out.println(emp.get());
		System.out.println("----------------------------------------------------------------------");

		// Find the min salary
		emp = list.stream().min(Comparator.comparingInt(e -> {
			return e.getSalary();
		}));
		System.out.println(emp.get());
		System.out.println("----------------------------------------------------------------------");

		// Find the average salary
		OptionalDouble avgSalary = list.stream().mapToInt(e -> e.getSalary().intValue()).average();
		System.out.println(avgSalary.getAsDouble());
		System.out.println("----------------------------------------------------------------------");

		// Find the 2nd largest salary
		Optional<Integer> secondHighestSalary = list.stream().map(Employee::getSalary).distinct().sorted().skip(1)
				.findFirst();
		System.out.println("2nd highest salary: " + secondHighestSalary.get());
		System.out.println("----------------------------------------------------------------------");

		// Find the employee with 2nd largest salary, if salary is same print print
		// employee whose name comes first in alphabetical order
		emp = list.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).filter(e -> {
			return e.getSalary() < list.stream().max(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
		}).sorted(Comparator.comparing(Employee::getName)).findFirst();
		
		emp = list.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).skip(2).findFirst();

		System.out.println("**Employee with 2nd highest salary: " + emp.get());
		System.out.println("----------------------------------------------------------------------");

		// Example of parallelStream
		list.parallelStream().forEach(System.out::println);
		System.out.println("----------------------------------------------------------------------");

		// Example of parallelStream with order
		list.parallelStream().forEachOrdered(System.out::println);
		System.out.println("----------------------------------------------------------------------");

		// Group by skill to map of list
		Map<String, List<Employee>> groupBySkills = list.stream().collect(Collectors.groupingBy(e -> e.getSkill()));
		System.out.println(groupBySkills);
		System.out.println("----------------------------------------------------------------------");

		// Group by skill to map of set
		Map<String, Set<Employee>> groupBySkillsUnique = list.stream()
				.collect(Collectors.groupingBy(Employee::getSkill, Collectors.toSet()));
		System.out.println(groupBySkillsUnique);
		System.out.println("----------------------------------------------------------------------");

		// Group by skill to treemap of list (sorted)
		Map<Integer, Set<Employee>> groupBySalarySorted = list.stream()
				.collect(Collectors.groupingBy(e -> e.getSalary(), TreeMap::new, Collectors.toSet()));
		System.out.println(groupBySalarySorted);
		System.out.println("----------------------------------------------------------------------");

	}

}
