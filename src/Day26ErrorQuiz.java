// Day 26 - Error Finding Quiz
// Find and fix the bugs

public class Day26ErrorQuiz {

    private String timeSlot;
    private String patientName;
    private boolean booked;

    public Day26ErrorQuiz(String timeSlot, String patientName) {
        timeSlot = timeSlot;          // Bug 1 - missing this
        this.patientName = patientName;
        booked = true;
    }

    public void cancel() {
        booked == false;              // Bug 2 - comparison not assignment
    }

    public boolean isBooked() {
        return booked;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public static void main(String[] args) {
        Day26ErrorQuiz appt = new Day26ErrorQuiz("09:00", "Alice")
        System.out.println(appt.isBooked());    // Bug 3 - missing semicolon above
        appt.cancel();
        System.out.println(appt.isBooked());
    }

}