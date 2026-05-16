// Day 36 - Error Finding Quiz
// Find and fix the bugs

public class Day36ErrorQuiz {

    private int current;
    private int stop;

    public Day36ErrorQuiz(int start, int stop) {
        current = start;
        stop = stop;          // Bug 1 - missing this
    }

    public boolean hasNext() {
        return current < stop;
    }

    public int next() {
        if (!hasNext()) {
            System.out.println("No more elements.");
            return -1;
        }
        return current =+ 1;  // Bug 2 - wrong operator, should be current++
    }

    public static void main(String[] args) {
        Day36ErrorQuiz range = new Day36ErrorQuiz(1, 5)
        while (range.hasNext()) {  // Bug 3 - missing semicolon above
            System.out.println(range.next());
        }
    }

}