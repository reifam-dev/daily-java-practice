// Day 52 - Error Finding Quiz
// Find and fix the bugs

public class Day52ErrorQuiz {

    private final String name;
    private final int age;
    private double averageGrade;

    public Day52ErrorQuiz(String name, int age, double averageGrade) {
        name = name;                  // Bug 1 - missing this
        age = age;                    // Bug 1 repeated
        this.averageGrade = averageGrade;
    }

    public boolean isPassing() {
        return averageGrade => 50.0;  // Bug 2 - invalid operator, should be >=
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + ", age " + age  // Bug 3 - missing semicolon
                + ", avg=" + averageGrade;
    }

    public static void main(String[] args) {
        Day52ErrorQuiz s = new Day52ErrorQuiz("Alice", 20, 85.0);
        System.out.println(s);
        System.out.println(s.isPassing());
    }

}