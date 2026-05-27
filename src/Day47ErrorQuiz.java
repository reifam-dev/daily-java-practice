// Day 47 - Error Finding Quiz
// Find and fix the bugs

public class Day47ErrorQuiz {

    private String name;
    private int size;

    public Day47ErrorQuiz(String name, int size) {
        name = name;              // Bug 1 - missing this
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void display(int indent) {
        String spaces = " ".repeat(indent);
        System.out.println(spaces + name + " (" + size + " KB)");  // Bug 2 - name null
    }

    public static void main(String[] args) {
        Day47ErrorQuiz file = new Day47ErrorQuiz("report.pdf", 500)
        file.display(0);   // Bug 3 - missing semicolon above
        file.display(4);
    }

}