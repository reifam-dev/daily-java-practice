// Day 61 - Error Finding Quiz
// Find and fix the bugs

public class Day61ErrorQuiz {

    enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    private Direction direction;
    private String label;

    public Day61ErrorQuiz(Direction direction, String label) {
        direction = direction;    // Bug 1 - missing this
        this.label = label;
    }

    public String getInfo() {
        return label + ": " + direction.name();
    }

    public static void main(String[] args) {
        Day61ErrorQuiz obj = new Day61ErrorQuiz(Direction.NORTH, "Heading")
        System.out.println(obj.getInfo());        // Bug 2 - missing semicolon above

        for (Direction d : Direction.values()) {
            System.out.println(d.name() + " = " + d.ordinal())
        }                                         // Bug 3 - missing semicolon
    }

}