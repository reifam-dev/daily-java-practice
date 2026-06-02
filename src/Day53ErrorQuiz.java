// Day 53 - Error Finding Quiz
// Find and fix the bugs

public class Day53ErrorQuiz {

    private final String name;
    private final String department;
    private final double salary;

    public Day53ErrorQuiz(String name, String department, double salary) {
        name = name;                  // Bug 1 - missing this
        department = department;      // Bug 1 repeated
        this.salary = salary;
    }

    public double getBonus(double percent) {
        return salary * percent / 100;
    }

    public boolean isHighEarner() {
        return salary => 50000.0;     // Bug 2 - invalid operator, should be >=
    }

    @Override
    public String toString() {
        return name + " (" + department + ") £" + salary;  // Bug 3 - name/dept null
    }

    public static void main(String[] args) {
        Day53ErrorQuiz emp = new Day53ErrorQuiz("Alice", "Engineering", 45000.0);
        System.out.println(emp);
        System.out.println(emp.getBonus(10));
        System.out.println(emp.isHighEarner());
    }

}