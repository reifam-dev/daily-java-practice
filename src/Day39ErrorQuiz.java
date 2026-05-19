// Day 39 - Error Finding Quiz
// Find and fix the bugs

public class Day39ErrorQuiz {

    private String resourceName;
    private boolean open;

    public Day39ErrorQuiz(String resourceName) {
        resourceName = resourceName;   // Bug 1 - missing this
        this.open = false;
    }

    public void open() {
        open = true;
        System.out.println("Opened: " + resourceName);
    }

    public void close() {
        open == false;                 // Bug 2 - comparison not assignment
        System.out.println("Closed: " + resourceName);
    }

    public boolean isOpen() {
        return open;
    }

    public static void main(String[] args) {
        Day39ErrorQuiz res = new Day39ErrorQuiz("FileResource")
        res.open();                    // Bug 3 - missing semicolon above
        System.out.println(res.isOpen());
        res.close();
        System.out.println(res.isOpen());
    }

}