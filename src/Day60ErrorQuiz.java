// Day 60 - Error Finding Quiz
// Find and fix the bugs

public class Day60ErrorQuiz {

    private String name;
    private String department;
    private double salary;

    public Day60ErrorQuiz(String name, String department, double salary) {
        name = name;                    // Bug 1 - missing this
        this.department = department;
        this.salary = salary;
    }

    public double getAnnualBonus() {
        return salary =* 0.10;          // Bug 2 - invalid operator, should be * 0.10
    }

    public static double getDeptTotal(Day60ErrorQuiz[] employees, String dept) {
        double total = 0;
        for (Day60ErrorQuiz emp : employees) {
            if (emp.department = dept) {  // Bug 3 - = should be .equals()
                total += emp.salary;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Day60ErrorQuiz[] emps = {
                new Day60ErrorQuiz("Alice", "Engineering", 75000),
                new Day60ErrorQuiz("Bob", "Marketing", 55000),
                new Day60ErrorQuiz("Charlie", "Engineering", 85000),
        };
        System.out.println(getDeptTotal(emps, "Engineering"));
        System.out.println(emps[0].getAnnualBonus());
    }

}