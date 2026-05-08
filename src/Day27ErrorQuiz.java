// Day 27 - Error Finding Quiz
// Find and fix the bugs

public class Day27ErrorQuiz {

    private String name;
    private double salary;

    public Day27ErrorQuiz(String name, double salary) {
        name = name;          // Bug 1 - missing this
        this.salary = salary;
    }

    public void givePayRise(double percentage) {
        salary *= 1 + percentage;   // Bug 2 - should divide by 100 first
    }

    public double getSalary() {
        return salary;
    }

    public static void main(String[] args) {
        Day27ErrorQuiz emp = new Day27ErrorQuiz("Alice", 30000)
        emp.givePayRise(10);        // Bug 3 - missing semicolon above
        System.out.println(emp.getSalary());
    }

}