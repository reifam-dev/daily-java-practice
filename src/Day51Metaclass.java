import java.util.HashMap;

public class Day51Metaclass {

    public static void main(String[] args) {

        System.out.println("=== Singleton via static getInstance() ===\n");
        DatabaseSingleton db1 = DatabaseSingleton.getInstance("localhost", 5432);
        DatabaseSingleton db2 = DatabaseSingleton.getInstance("remotehost", 9999);

        System.out.println("  db1 == db2   : " + (db1 == db2));
        System.out.println("  db1 host     : " + db1.getHost());
        System.out.println("  db2 host     : " + db2.getHost());
        System.out.println("  " + db1.connect() + "\n");

        System.out.println("=== ClassTracker — tracks instance counts ===\n");
        ClassTracker tracker = new ClassTracker();
        tracker.register("DatabaseSingleton");
        tracker.register("ConfigManager");
        tracker.increment("DatabaseSingleton");
        tracker.increment("DatabaseSingleton");
        tracker.increment("ConfigManager");

        System.out.println("  DatabaseSingleton : "
                + tracker.getCount("DatabaseSingleton"));
        System.out.println("  ConfigManager     : "
                + tracker.getCount("ConfigManager"));

    }

}

class DatabaseSingleton {

    private static DatabaseSingleton instance = null;
    private String host;
    private int port;

    private DatabaseSingleton(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static DatabaseSingleton getInstance(String host, int port) {
        if (instance == null) {
            instance = new DatabaseSingleton(host, port);
        }
        return instance;
    }

    public String getHost() { return host; }
    public int getPort() { return port; }

    public String connect() {
        return "Connected to " + host + ":" + port;
    }

    @Override
    public String toString() {
        return String.format("Database('%s', %d)", host, port);
    }

}

class ClassTracker {

    private HashMap<String, Integer> counts = new HashMap<>();

    public void register(String className) {
        counts.put(className, 0);
    }

    public void increment(String className) {
        if (counts.containsKey(className)) {
            counts.put(className, counts.get(className) + 1);
        } else {
            System.out.println("Class '" + className + "' not registered.");
        }
    }

    public int getCount(String className) {
        return counts.getOrDefault(className, 0);
    }

}