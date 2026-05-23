// Day 41 - Error Finding Quiz
// Find and fix the bugs

public class Day41ErrorQuiz {

    private String shapeName;
    private double dimension;

    public Day41ErrorQuiz(String shapeName, double dimension) {
        shapeName = shapeName;       // Bug 1 - missing this
        this.dimension = dimension;
    }

    public double getArea() {
        return dimension * dimension;
    }

    public double getPerimeter() {
        return dimension =+ 4;       // Bug 2 - wrong operator, should be * 4
    }

    @Override
    public String toString() {
        return shapeName + " dim=" + dimension;   // Bug 3 - shapeName null
    }

    public static void main(String[] args) {
        Day41ErrorQuiz s = new Day41ErrorQuiz("Square", 5.0);
        System.out.println(s);
        System.out.println(s.getArea());
        System.out.println(s.getPerimeter());
    }

}