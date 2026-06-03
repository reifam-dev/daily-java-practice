// Day 54 - Error Finding Quiz
// Find and fix the bugs

public class Day54ErrorQuiz {

    private String operation;
    private int operand;

    public Day54ErrorQuiz(String operation, int operand) {
        operation = operation;    // Bug 1 - missing this
        this.operand = operand;
    }

    public int apply(int value) {
        if (operation.equals("add")) {
            return value + operand;
        } else if (operation.equals("multiply")) {
            return value =* operand;  // Bug 2 - invalid operator, should be * operand
        }
        return value;
    }

    public static int reduce(int[] numbers) {
        int total = 0;
        for (int n : numbers) {
            total =+ n;              // Bug 3 - wrong operator, should be +=
        }
        return total;
    }

    public static void main(String[] args) {
        Day54ErrorQuiz adder = new Day54ErrorQuiz("add", 10);
        Day54ErrorQuiz multiplier = new Day54ErrorQuiz("multiply", 3);
        System.out.println(adder.apply(5));
        System.out.println(multiplier.apply(5));
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(reduce(nums));
    }

}