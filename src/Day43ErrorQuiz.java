// Day 43 - Error Finding Quiz
// Find and fix the bugs

public class Day43ErrorQuiz {

    private String observerName;
    private boolean active;

    public Day43ErrorQuiz(String observerName) {
        observerName = observerName;   // Bug 1 - missing this
        active = true;
    }

    public void notify(String message) {
        if (active == true) {
            System.out.println(observerName + ": " + message);
        }
    }

    public void deactivate() {
        active == false;               // Bug 2 - comparison not assignment
    }

    public static void main(String[] args) {
        Day43ErrorQuiz obs = new Day43ErrorQuiz("EmailObserver")
        obs.notify("System update");   // Bug 3 - missing semicolon above
        obs.deactivate();
        obs.notify("Should not print");
    }

}