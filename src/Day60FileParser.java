import java.util.ArrayList;
import java.util.HashMap;

public class Day60FileParser {

    public static void main(String[] args) {

        ArrayList<Employee60> employees = new ArrayList<>();
        employees.add(new Employee60("Alice", "Engineering", 75000));
        employees.add(new Employee60("Bob", "Marketing", 55000));
        employees.add(new Employee60("Charlie", "Engineering", 85000));
        employees.add(new Employee60("Diana", "HR", 50000));
        employees.add(new Employee60("Eve", "Marketing", 62000));
        employees.add(new Employee60("Frank", "Engineering", 90000));

        System.out.println("=== All employees ===\n");
        for (Employee60 emp : employees) {
            System.out.println("  " + emp);
        }

        System.out.println("\n=== Department summary ===\n");
        HashMap<String, double[]> summary = new HashMap<>();
        for (Employee60 emp : employees) {
            summary.putIfAbsent(emp.getDepartment(), new double[]{0, 0});
            summary.get(emp.getDepartment())[0]++;
            summary.get(emp.getDepartment())[1] += emp.getSalary();
        }
        for (String dept : summary.keySet()) {
            double count = summary.get(dept)[0];
            double total = summary.get(dept)[1];
            System.out.printf("  %-15s count=%.0f  total=£%.0f  avg=£%.0f%n",
                    dept, count, total, total / count);
        }

        System.out.println("\n=== Highest earner ===\n");
        Employee60 top = employees.get(0);
        for (Employee60 emp : employees) {
            if (emp.getSalary() > top.getSalary()) top = emp;
        }
        System.out.println("  " + top);
        System.out.printf("  Bonus (10%%): £%.0f%n", top.getAnnualBonus());

    }

}

class Employee60 {

    private String name;
    private String department;
    private double salary;

    public Employee60(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public double getAnnualBonus() {
        return salary * 0.10;
    }

    @Override
    public String toString() {
        return String.format("Employee(name='%s', dept='%s', salary=£%.0f)",
                name, department, salary);
    }

}