// Day 14 - Simple Error Finding Quiz

public class Day14ErrorQuiz {

    public static void main(String[] args) {

        BookTracker tracker = new BookTracker();
        tracker.addBook("1984", "Orwell");
        tracker.markAsRead("1984");
        System.out.println(tracker.getUnreadCount());

    }

}