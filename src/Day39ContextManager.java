public class Day39ContextManager {

    public static void main(String[] args) {

        // Java uses try-with-resources as its context manager equivalent
        // Any class implementing AutoCloseable can be used

        System.out.println("=== try-with-resources (AutoCloseable) ===\n");

        try (ManagedResource res = new ManagedResource("DatabaseConnection")) {
            System.out.println("  Resource open: " + res.isOpen());
            res.doWork("SELECT * FROM users");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        // close() called automatically even if exception occurs

        System.out.println();

        System.out.println("=== Multiple resources in try-with-resources ===\n");

        try (ManagedResource r1 = new ManagedResource("FileReader");
             ManagedResource r2 = new ManagedResource("FileWriter")) {
            r1.doWork("Reading file");
            r2.doWork("Writing file");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        // r2 closed first, then r1 — reverse order of declaration

    }

}

class ManagedResource implements AutoCloseable {

    private String name;
    private boolean open;

    public ManagedResource(String name) {
        this.name = name;
        this.open = true;
        System.out.println("  Opened: " + name);
    }

    public boolean isOpen() {
        return open;
    }

    public void doWork(String task) {
        if (!open) {
            System.out.println("  Resource is closed: " + name);
            return;
        }
        System.out.println("  " + name + " doing: " + task);
    }

    @Override
    public void close() {
        open = false;
        System.out.println("  Closed: " + name);
    }

}