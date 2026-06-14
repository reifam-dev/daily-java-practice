// Day 65 - Error Finding Quiz
// Find and fix the bugs

public class Day65ErrorQuiz {

    private String name;
    private int[] scores;

    public Day65ErrorQuiz(String name, int[] scores) {
        name = name;              // Bug 1 - missing this
        this.scores = scores;
    }

    public Day65ErrorQuiz shallowCopy() {
        return new Day65ErrorQuiz(this.name, this.scores);  // Bug 2 - shares array reference
    }

    public int getScore(int index) {
        return scores[index];
    }

    public void setScore(int index, int value) {
        scores[index] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + ": [");
        for (int i = 0; i < scores.length; i++) {
            sb.append(scores[i]);
            if (i < scores.length - 1) sb.append(", ");
        }
        sb.append("]")
        return sb.toString();     // Bug 3 - missing semicolon above
    }

    public static void main(String[] args) {
        int[] s = {85, 90, 78};
        Day65ErrorQuiz orig = new Day65ErrorQuiz("Alice", s);
        Day65ErrorQuiz copy = orig.shallowCopy();
        copy.setScore(0, 99);
        System.out.println(orig);  // Bug 2 demonstrates - orig also changed
        System.out.println(copy);
    }

}