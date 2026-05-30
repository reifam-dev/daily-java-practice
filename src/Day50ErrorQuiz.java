// Day 50 - Error Finding Quiz
// Find and fix the bugs

public class Day50ErrorQuiz {

    private final int x;
    private final int y;

    public Day50ErrorQuiz(int x, int y) {
        x = x;                    // Bug 1 - missing this
        y = y;                    // Bug 1 repeated
    }

    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        Day50ErrorQuiz p = new Day50ErrorQuiz(3, 4)
        System.out.println(p);                    // Bug 3 - missing semicolon above
        System.out.printf("Distance: %.2f%n",
                p.distanceFromOrigin());
        System.out.println(p.getX() =+ 10);       // Bug 2 - wrong operator, should be +
    }

}