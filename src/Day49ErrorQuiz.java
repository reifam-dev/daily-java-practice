// Day 49 - Error Finding Quiz
// Find and fix the bugs

public class Day49ErrorQuiz {

    private String name;
    private int callCount;

    public Day49ErrorQuiz(String name) {
        name = name;              // Bug 1 - missing this
        this.callCount = 0;
    }

    public String call(String message) {
        callCount =+ 1;           // Bug 2 - wrong operator, should be ++
        return name + " says: " + message;
    }

    public int getCallCount() {
        return callCount;
    }

    @Override
    public String toString() {
        return name + " called " + callCount + " times";  // Bug 3 - name null
    }

    public static void main(String[] args) {
        Day49ErrorQuiz obj = new Day49ErrorQuiz("Greeter");
        System.out.println(obj.call("Hello"));
        System.out.println(obj.call("World"));
        System.out.println(obj);
    }

}