public class Day27EmployeeRecords {

    public static void main(String[] args) {

        Employee alice = new Employee(1, "Alice", 30000);
        Employee bob = new Employee(2, "Bob", 45000);

        System.out.println(alice.getName()
                + " salary: £" + alice.getSalary());
        System.out.println(bob.getName()
                + " salary: £" + bob.getSalary());

        alice.givePayRise(10);
        System.out.printf("Alice after 10%% rise: £%.2f%n",
                alice.getSalary());

        bob.givePayRise(5);
        System.out.printf("Bob after 5%% rise: £%.2f%n",
                bob.getSalary());

        bob.givePayRise(-5);   // Should print warning - invalid percentage

    }

}

class Employee {

    private int empId;
    private String name;
    private double salary;

    public Employee(int empId, String name, double salary) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void givePayRise(double percentage) {
        if (percentage <= 0) {
            System.out.println("Pay rise percentage must be positive.");
        } else {
            salary *= 1 + (percentage / 100);
        }
    }

}