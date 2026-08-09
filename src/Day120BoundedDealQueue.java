import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * A FIFO queue with a fixed maximum size, applying backpressure by
 * rejecting new items once at capacity rather than growing
 * unboundedly.
 */
final class BoundedQueue {
    private final int maxSize;
    private final Deque<String> items;

    public BoundedQueue(int maxSize) {
        this.maxSize = maxSize;
        this.items = new ArrayDeque<>();
    }

    public boolean push(String item) {
        if (this.items.size() >= this.maxSize) {
            return false;
        }
        this.items.addLast(item);
        return true;
    }

    public String pop() {
        return this.items.pollFirst();
    }

    public int size() {
        return this.items.size();
    }

    public int getMaxSize() {
        return this.maxSize;
    }
}

public class Day120BoundedDealQueue {

    private static final int MAX_QUEUE_SIZE = 3;

    public static void main(String[] args) {
        BoundedQueue queue = new BoundedQueue(MAX_QUEUE_SIZE);
        List<String> rejected = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            String item = "deal-" + i;
            boolean accepted = queue.push(item);

            if (accepted) {
                System.out.println("Accepted: " + item + " (queue size: " + queue.size() + ")");
            } else {
                rejected.add(item);
                System.out.println("Rejected (queue full): " + item);
                continue;
            }

            if (queue.size() >= queue.getMaxSize()) {
                String processed = queue.pop();
                System.out.println("Processed: " + processed);
            }
        }

        System.out.println("Rejected count: " + rejected.size());
    }
}