// Day 51 - Error Finding Quiz
// Find and fix the bugs

public class Day51ErrorQuiz {

    private String className;
    private int instanceCount;

    public Day51ErrorQuiz(String className) {
        className = className;        // Bug 1 - missing this
        this.instanceCount = 0;
    }

    public void createInstance() {
        instanceCount =+ 1;           // Bug 2 - wrong operator, should be ++
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    @Override
    public String toString() {
        return className + ": " + instanceCount + " instances";  // Bug 3 - null
    }

    public static void main(String[] args) {
        Day51ErrorQuiz tracker = new Day51ErrorQuiz("DatabaseClass");
        tracker.createInstance();
        tracker.createInstance();
        tracker.createInstance();
        System.out.println(tracker);
        System.out.println(tracker.getInstanceCount());
    }

}