// Day 34 - Error Finding Quiz
// Find and fix the bugs

public class Day34ErrorQuiz {

    private String title;
    private int priority;

    public Day34ErrorQuiz(String title, int priority) {
        title = title;            // Bug 1 - missing this
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Day34ErrorQuiz)) return false;
        Day34ErrorQuiz other = (Day34ErrorQuiz) obj;
        return priority == other.priority;  // Bug 2 - should also compare title
    }

    public static void main(String[] args) {
        Day34ErrorQuiz n1 = new Day34ErrorQuiz("Email", 2)
        Day34ErrorQuiz n2 = new Day34ErrorQuiz("SMS", 2);  // Bug 3 - missing semicolon above
        System.out.println(n1.equals(n2));
        System.out.println(n1.getTitle());
    }

}