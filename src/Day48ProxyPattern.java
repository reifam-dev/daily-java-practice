import java.util.ArrayList;

public class Day48ProxyPattern {

    public static void main(String[] args) {

        System.out.println("=== VirtualProxy — lazy initialisation ===\n");
        DatabaseProxy proxy = new VirtualProxy("ProductionDB");
        System.out.println("  Proxy created — no connection yet");
        System.out.println("  " + proxy.query("SELECT * FROM users"));
        System.out.println("  " + proxy.query("SELECT * FROM orders"));

        System.out.println("\n=== LoggingProxy — audit trail ===\n");
        LoggingProxy logged = new LoggingProxy("AuditDB");
        logged.query("SELECT * FROM transactions");
        logged.query("SELECT * FROM accounts");
        System.out.println("  Query log:");
        for (String entry : logged.getLog()) {
            System.out.println("    " + entry);
        }

    }

}

interface DatabaseProxy {
    String query(String sql);
}

class RealDatabase {

    private String name;

    public RealDatabase(String name) {
        System.out.println("  [RealDatabase] Connecting to '" + name + "'...");
        this.name = name;
    }

    public String query(String sql) {
        return "[" + name + "] Result: " + sql;
    }

}

class VirtualProxy implements DatabaseProxy {

    private String name;
    private RealDatabase real = null;

    public VirtualProxy(String name) {
        this.name = name;
    }

    public String query(String sql) {
        if (real == null) {
            real = new RealDatabase(name);
        }
        return real.query(sql);
    }

}

class LoggingProxy implements DatabaseProxy {

    private String name;
    private RealDatabase real = null;
    private ArrayList<String> log = new ArrayList<>();

    public LoggingProxy(String name) {
        this.name = name;
    }

    public String query(String sql) {
        if (real == null) {
            real = new RealDatabase(name);
        }
        String result = real.query(sql);
        log.add("SQL: " + sql + " | Result: " + result);
        return result;
    }

    public ArrayList<String> getLog() {
        return log;
    }

}