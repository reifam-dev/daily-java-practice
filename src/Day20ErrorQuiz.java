// Day 20 - Error Finding Quiz
// Find and fix the bugs

public class Day20ErrorQuiz {

    private String title;
    private int copies;

    public Day20ErrorQuiz(String title, int copies) {
        title = title;        // Bug 1
        copies = copies;      // Bug 1 repeated
    }

    public boolean isAvailable() {
        return copies > 0;
    }

    public void borrowBook() {
        copies--;             // Bug 2 - no check if copies > 0
    }

    public static void main(String[] args) {
        Day20ErrorQuiz book = new Day20ErrorQuiz("1984", 1)
        book.borrowBook();    // Bug 3 - missing semicolon above
        book.borrowBook();
        System.out.println(book.isAvailable());
    }

}