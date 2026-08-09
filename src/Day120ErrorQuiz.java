import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class BoundedQueue {
    private int maxSize;
    private Deque<String> items;

    public BoundedQueue(int maxSize) {
        this.maxSize = maxSize;
        this.items = new ArrayDeque<>();
    }

    public boolean push(String item) {
        items.add(item);
        return true;
    }

    public String pop() {
        return items.pollFirst();
    }

    public int size() {
        return items.size();
    }
}

public class Day120ErrorQuiz {

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
                rejected.add(item)
            }

            if (queue.size() >= MAX_QUEUE_SIZE) {
                String processed = queue.pop();
                System.out.println("Processed: " + processed);
            }
        }

        System.out.println("Rejected count: " + rejected.size());
    }
}