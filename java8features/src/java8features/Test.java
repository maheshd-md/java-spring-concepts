package java8features;

import java.util.*;
import java.util.function.DoubleConsumer;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

        List<Employee> list = new ArrayList<>();

        list.add(new Employee("Mahesh", 100000, "Java"));
        list.add(new Employee("Saurabh", 200000, "Java"));
        list.add(new Employee("Akshay", 200000, "Salesforce"));
        list.add(new Employee("Guru", 50000, "Java"));
        list.add(new Employee("Sagar", 50000, "Java"));

        Optional<Employee> empOp = list.stream().max(Comparator.comparingInt(Employee::getSalary));
        Integer maxSal = empOp.get().getSalary();
        System.out.println(maxSal);
        System.out.println(list.stream().map(Employee::getSalary).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().get());

        list.stream().mapToInt(Employee::getSalary).average().ifPresent(System.out::println);
    }
}
