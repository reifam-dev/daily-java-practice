// Day 21 - Error Finding Quiz
// Find and fix the bugs

public class Day21ErrorQuiz {

    private String name;
    private boolean isActive;

    public Day21ErrorQuiz(String name) {
        this.name = name;
        isActive = true;
    }

    public void cancelMembership() {
        isActive == false;   // Bug 1 - comparison instead of assignment
    }

    public boolean isActive() {
        return isActive;
    }

    public static void main(String[] args) {
        Day21ErrorQuiz member = new Day21ErrorQuiz("Alice")   // Bug 2
        System.out.println(member.isActive());
        member.cancelMembership();
        System.out.println(member.isActive());
    }

}