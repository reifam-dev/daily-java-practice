// Day 28 - Error Finding Quiz
// Find and fix the bugs

public class Day28ErrorQuiz {

    private String ticketHolder;
    private boolean booked;
    private int ticketNumber;

    public Day28ErrorQuiz(int ticketNumber, String ticketHolder) {
        ticketNumber = ticketNumber;   // Bug 1 - missing this
        this.ticketHolder = ticketHolder;
        booked = true;
    }

    public void cancel() {
        booked == false;               // Bug 2 - comparison not assignment
    }

    public boolean isBooked() {
        return booked;
    }

    public static void main(String[] args) {
        Day28ErrorQuiz t = new Day28ErrorQuiz(101, "Alice")
        t.cancel();                    // Bug 3 - missing semicolon above
        System.out.println(t.isBooked());
    }

}