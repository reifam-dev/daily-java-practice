import java.util.ArrayList;
import java.util.Collections;

public class Day34NotificationSystem {

    public static void main(String[] args) {

        Notification n1 = new Notification("Email", 3);
        Notification n2 = new Notification("SMS", 1);
        Notification n3 = new Notification("Push Alert", 2);
        Notification n4 = new Notification("Email", 3);

        System.out.println("n1 equals n4: " + n1.equals(n4));  // true
        System.out.println("n1 equals n2: " + n1.equals(n2));  // false
        System.out.println("n1 == n4    : " + (n1 == n4));     // false

        ArrayList<Notification> list = new ArrayList<>();
        list.add(n1);
        list.add(n2);
        list.add(n3);

        Collections.sort(list);
        System.out.println("\nSorted by priority:");
        for (Notification n : list) {
            System.out.println("  " + n);
        }

    }

}

class Notification implements Comparable<Notification> {

    private String title;
    private int priority;

    public Notification(String title, int priority) {
        this.title = title;
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
        if (!(obj instanceof Notification)) return false;
        Notification other = (Notification) obj;
        return this.title.equals(other.title)
                && this.priority == other.priority;
    }

    @Override
    public int compareTo(Notification other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return String.format("Notification(title='%s', priority=%d)",
                title, priority);
    }

}