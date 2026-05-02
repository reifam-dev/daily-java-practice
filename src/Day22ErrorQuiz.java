// Day 22 - Error Finding Quiz
// Find and fix the bugs

public class Day22ErrorQuiz {

    private int roomNumber;
    private boolean isBooked;

    public Day22ErrorQuiz(int roomNumber) {
        roomNumber = roomNumber;    // Bug 1 - missing this
        this.isBooked = false;
    }

    public void bookRoom() {
        if (isBooked = true) {      // Bug 2 - assignment instead of comparison
            System.out.println("Already booked.");
        } else {
            isBooked = true;
        }
    }

    public boolean isAvailable() {
        return !isBooked;
    }

    public static void main(String[] args) {
        Day22ErrorQuiz room = new Day22ErrorQuiz(101);
        room.bookRoom();
        System.out.println(room.isAvailable())
    }                               // Bug 3 - missing semicolon above

}