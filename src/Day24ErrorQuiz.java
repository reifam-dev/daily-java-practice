// Day 24 - Error Finding Quiz
// Find and fix the bugs

public class Day24ErrorQuiz {

    private String registration;
    private boolean active;

    public Day24ErrorQuiz(String registration) {
        registration = registration;   // Bug 1 - missing this
        active = true;
    }

    public void deregister() {
        active == false;               // Bug 2 - comparison not assignment
    }

    public boolean isActive() {
        return active;
    }

    public static void main(String[] args) {
        Day24ErrorQuiz v = new Day24ErrorQuiz("AB12CDE")
        System.out.println(v.isActive());   // Bug 3 - missing semicolon above
        v.deregister();
        System.out.println(v.isActive());
    }

}