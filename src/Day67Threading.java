import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Day67Threading {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== Thread-safe counter with AtomicInteger ===\n");
        AtomicInteger counter = new AtomicInteger(0);
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.incrementAndGet();
                }
            });
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("  Expected : 5000");
        System.out.println("  Got      : " + counter.get());

        System.out.println("\n=== Named worker threads ===\n");
        Thread worker1 = new Thread(() -> {
            System.out.println("  Worker-1 started");
            try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            System.out.println("  Worker-1 done");
        }, "Worker-1");

        Thread worker2 = new Thread(() -> {
            System.out.println("  Worker-2 started");
            try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            System.out.println("  Worker-2 done");
        }, "Worker-2");

        worker1.start();
        worker2.start();
        worker1.join();
        worker2.join();
        System.out.println("  All workers complete.");

    }

}