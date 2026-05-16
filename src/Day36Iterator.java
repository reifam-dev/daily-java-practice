import java.util.ArrayList;
import java.util.Iterator;

public class Day36Iterator {

    public static void main(String[] args) {

        // NumberRange iterator
        NumberRange range = new NumberRange(1, 6);
        System.out.println("NumberRange 1 to 5:");
        while (range.hasNext()) {
            System.out.println("  " + range.next());
        }

        range.reset();
        System.out.println("\nAfter reset:");
        while (range.hasNext()) {
            System.out.print(range.next() + " ");
        }
        System.out.println();

        // CountDown iterator
        CountDown cd = new CountDown(5);
        System.out.println("\nCountDown from 5:");
        while (cd.hasNext()) {
            System.out.println("  " + cd.next());
        }

        // ArrayList with built-in Iterator
        ArrayList<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        System.out.println("\nArrayList with Iterator:");
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            System.out.println("  " + it.next());
        }

    }

}

class NumberRange {

    private int current;
    private int start;
    private int stop;

    public NumberRange(int start, int stop) {
        this.start = start;
        this.current = start;
        this.stop = stop;
    }

    public boolean hasNext() {
        return current < stop;
    }

    public int next() {
        if (!hasNext()) {
            throw new RuntimeException("No more elements.");
        }
        return current++;
    }

    public void reset() {
        this.current = this.start;
    }

}

class CountDown {

    private int current;
    private int start;

    public CountDown(int start) {
        this.start = start;
        this.current = start;
    }

    public boolean hasNext() {
        return current >= 0;
    }

    public int next() {
        if (!hasNext()) {
            throw new RuntimeException("No more elements.");
        }
        return current--;
    }

    public void reset() {
        this.current = this.start;
    }

}