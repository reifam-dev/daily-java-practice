// Day 67 - Error Finding Quiz
// Find and fix the bugs

public class Day67ErrorQuiz {

    private String threadName;
    private int iterations;
    private static int counter = 0;

    public Day67ErrorQuiz(String threadName, int iterations) {
        threadName = threadName;      // Bug 1 - missing this
        this.iterations = iterations;
    }

    public void run() {
        for (int i = 0; i < iterations; i++) {
            counter =+ 1;             // Bug 2 - wrong operator, should be ++
        }
        System.out.println(threadName + " finished. Counter: " + counter)
    }                                 // Bug 3 - missing semicolon

    public static void main(String[] args) throws InterruptedException {
        Day67ErrorQuiz r1 = new Day67ErrorQuiz("Thread-1", 1000);
        Day67ErrorQuiz r2 = new Day67ErrorQuiz("Thread-2", 1000);
        Thread t1 = new Thread(() -> r1.run());
        Thread t2 = new Thread(() -> r2.run());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final counter: " + counter);
    }

}