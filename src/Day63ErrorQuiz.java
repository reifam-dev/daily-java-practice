// Day 63 - Error Finding Quiz
// Find and fix the bugs

public class Day63ErrorQuiz {

    private String name;
    private int age;
    private boolean frozen;

    public Day63ErrorQuiz(String name, int age) {
        name = name;              // Bug 1 - missing this
        this.age = age;
        this.frozen = false;
    }

    public void freeze() {
        frozen = true;
    }

    public void setName(String name) {
        if (frozen == true) {     // Bug 2 - should use !frozen or frozen == false check
            System.out.println("Object is frozen.");
            return;
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ", age " + age;  // Bug 3 - name null due to Bug 1
    }

    public static void main(String[] args) {
        Day63ErrorQuiz obj = new Day63ErrorQuiz("Alice", 30);
        System.out.println(obj);
        obj.freeze();
        obj.setName("Bob");
        System.out.println(obj);
    }

}