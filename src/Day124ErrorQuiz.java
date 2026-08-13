import java.util.LinkedHashMap;
import java.util.Map;

public class Day124ErrorQuiz {

    private boolean databaseConnected;
    private boolean cacheWarmed;

    public Map<String, Object> liveness() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", "ok");
        return result;
    }

    public Map<String, Object> readiness() {
        Map<String, Boolean> checks = new LinkedHashMap<>();
        checks.put("database", databaseConnected);
        checks.put("cache", cacheWarmed);

        boolean allReady = true;
        for (boolean value : checks.values()) {
            allReady = value;
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", allReady ? "ready" : "not_ready");
        result.put("checks", checks);
        return result;
    }

    private void simulateStartup() {
        databaseConnected = true;
        System.out.println("Database connected");
        cacheWarmed = true
        System.out.println("Cache warmed");
    }

    public static void main(String[] args) {
        Day124ErrorQuiz health = new Day124ErrorQuiz();

        System.out.println("Before startup:");
        System.out.println(" liveness: " + health.liveness());
        System.out.println(" readiness: " + health.readiness());

        health.simulateStartup();

        System.out.println("\nAfter startup:");
        System.out.println(" liveness: " + health.liveness());
        System.out.println(" readiness: " + health.readiness());
    }
}