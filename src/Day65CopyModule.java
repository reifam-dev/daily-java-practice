import java.util.Arrays;

public class Day65CopyModule {

    public static void main(String[] args) {

        int[] scores = {85, 90, 78};
        Student orig = new Student("Alice", scores);

        System.out.println("=== Shallow copy (shared array) ===\n");
        Student shallow = orig.shallowCopy();
        shallow.setScore(0, 99);
        System.out.println("  Original : " + orig);
        System.out.println("  Shallow  : " + shallow);
        System.out.println("  Same array: " + (orig.getScores() == shallow.getScores()));

        int[] scores2 = {85, 90, 78};
        Student orig2 = new Student("Alice", scores2);

        System.out.println("\n=== Deep copy (independent array) ===\n");
        Student deep = orig2.deepCopy();
        deep.setScore(0, 99);
        System.out.println("  Original : " + orig2);
        System.out.println("  Deep     : " + deep);
        System.out.println("  Same array: " + (orig2.getScores() == deep.getScores()));

    }

}

class Student {

    private String name;
    private int[] scores;

    public Student(String name, int[] scores) {
        this.name = name;
        this.scores = scores;
    }

    public String getName() { return name; }
    public int[] getScores() { return scores; }

    public void setScore(int index, int value) {
        if (index >= 0 && index < scores.length) {
            scores[index] = value;
        }
    }

    public Student shallowCopy() {
        return new Student(this.name, this.scores);  // shares array
    }

    public Student deepCopy() {
        return new Student(this.name, Arrays.copyOf(this.scores, this.scores.length));
    }

    @Override
    public String toString() {
        return String.format("Student(name='%s', scores=%s)",
                name, Arrays.toString(scores));
    }

}