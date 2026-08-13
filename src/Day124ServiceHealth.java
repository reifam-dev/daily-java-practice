import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Tracks a service's startup state, distinguishing a liveness check
 * (is the process running at all) from a readiness check (has startup
 * completed and can this instance safely receive traffic).
 */
public class Day124ServiceHealth {

    private boolean databaseConnected;
    private boolean cacheWarmed;

    public Map<String, Object> liveness() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", "ok");
        return result;
    }

    public Map<String, Object> readiness() {
        Map<String, Boolean> checks = new LinkedHashMap<>();
        checks.put("database", this.databaseConnected);
        checks.put("cache", this.cacheWarmed);

        boolean allReady = true;
        for (boolean value : checks.values()) {
            allReady = allReady && value;
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", allReady ? "ready" : "not_ready");
        result.put("checks", checks);
        return result;
    }

    private void simulateStartup() {
        this.databaseConnected = true;
        System.out.println("Database connected");
        this.cacheWarmed = true;
        System.out.println("Cache warmed");
    }

    public static void main(String[] args) {
        Day124ServiceHealth health = new Day124ServiceHealth();

        System.out.println("Before startup:");
        System.out.println(" liveness: " + health.liveness());
        System.out.println(" readiness: " + health.readiness());

        health.simulateStartup();

        System.out.println("\nAfter startup:");
        System.out.println(" liveness: " + health.liveness());
        System.out.println(" readiness: " + health.readiness());
    }
}