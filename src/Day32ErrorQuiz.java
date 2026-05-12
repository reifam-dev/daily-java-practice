// Day 32 - Error Finding Quiz
// Find and fix the bugs

public class Day32ErrorQuiz {

    private String name;
    private String species;
    private int age;

    public Day32ErrorQuiz(String name, String species, int age) {
        name = name;          // Bug 1 - missing this
        this.species = species;
        this.age = age;
    }

    public void haveBirthday() {
        age =+ 1;             // Bug 2 - wrong operator, should be +=
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return name + " (" + species + "), age " + age;
    }

    public static void main(String[] args) {
        Day32ErrorQuiz a = new Day32ErrorQuiz("Leo", "Lion", 5)
        a.haveBirthday();     // Bug 3 - missing semicolon above
        System.out.println(a);
    }

}