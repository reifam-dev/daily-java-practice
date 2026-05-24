// Day 44 - Error Finding Quiz
// Find and fix the bugs

public class Day44ErrorQuiz {

    private String name;
    private int[] data;

    public Day44ErrorQuiz(String name, int[] data) {
        name = name;              // Bug 1 - missing this
        this.data = data;
    }

    public int getMin() {
        int min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }

    public int getMax() {
        int max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max)
                max =+ data[i];   // Bug 2 - wrong operator, should be = data[i]
        }
        return max;
    }

    @Override
    public String toString() {
        return name + " min=" + getMin() + " max=" + getMax();
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 8, 1, 9, 2}
        Day44ErrorQuiz d = new Day44ErrorQuiz("Data", nums);  // Bug 3 - missing semicolon above
        System.out.println(d);
    }

}