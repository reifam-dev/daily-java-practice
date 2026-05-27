public class Day46SingletonPattern {

    public static void main(String[] args) {

        System.out.println("=== DatabaseSingleton ===\n");
        DatabaseSingleton db1 = DatabaseSingleton.getInstance("localhost", 5432);
        DatabaseSingleton db2 = DatabaseSingleton.getInstance("remotehost", 9999);

        System.out.println("db1 == db2   : " + (db1 == db2));
        System.out.println("db1 host     : " + db1.getHost());
        System.out.println("db2 host     : " + db2.getHost());
        System.out.println(db1.connect());
        System.out.println(db1 + "\n");

        System.out.println("=== ConfigSingleton ===\n");
        ConfigSingleton config1 = ConfigSingleton.getInstance();
        config1.set("theme", "dark");
        config1.set("language", "en-GB");

        ConfigSingleton config2 = ConfigSingleton.getInstance();
        System.out.println("config1 == config2 : " + (config1 == config2));
        System.out.println("theme              : " + config2.get("theme"));

    }

}

class DatabaseSingleton {

    private static DatabaseSingleton instance = null;
    private String host;
    private int port;
    private boolean connected;

    private DatabaseSingleton(String host, int port) {
        this.host = host;
        this.port = port;
        this.connected = false;
    }

    public static DatabaseSingleton getInstance(String host, int port) {
        if (instance == null) {
            instance = new DatabaseSingleton(host, port);
        }
        return instance;
    }

    public String getHost() { return host; }
    public int getPort() { return port; }
    public boolean isConnected() { return connected; }

    public String connect() {
        connected = true;
        return "Connected to " + host + ":" + port;
    }

    @Override
    public String toString() {
        return String.format("DatabaseSingleton(host='%s', port=%d, connected=%b)",
                host, port, connected);
    }

}

class ConfigSingleton {

    private static ConfigSingleton instance = null;
    private java.util.HashMap<String, String> settings;

    private ConfigSingleton() {
        settings = new java.util.HashMap<>();
    }

    public static ConfigSingleton getInstance() {
        if (instance == null) {
            instance = new ConfigSingleton();
        }
        return instance;
    }

    public void set(String key, String value) {
        settings.put(key, value);
    }

    public String get(String key) {
        return settings.getOrDefault(key, null);
    }

}