public class Day14BookTracker {

    public static void main(String[] args) {

        BookTracker tracker = new BookTracker();
        tracker.addBook("1984", "George Orwell");
        tracker.addBook("Clean Code", "Robert Martin");
        tracker.addBook("The Pragmatic Programmer", "Hunt and Thomas");

        tracker.markAsRead("1984");

        System.out.println("Total books  : " + tracker.getTotalCount());
        System.out.println("Unread books : " + tracker.getUnreadCount());

    }

}

class BookTracker {

    private String[] titles = new String[20];
    private boolean[] readStatus = new boolean[20];
    private int count = 0;

    public void addBook(String title, String author) {
        if (count < titles.length) {
            titles[count] = title;
            readStatus[count] = false;
            count++;
        }
    }

    public void markAsRead(String title) {
        for (int i = 0; i < count; i++) {
            if (titles[i].equals(title)) {
                readStatus[i] = true;
                return;
            }
        }
    }

    public int getUnreadCount() {
        int unread = 0;
        for (int i = 0; i < count; i++) {
            if (!readStatus[i]) unread++;
        }
        return unread;
    }

    public int getTotalCount() {
        return count;
    }

}