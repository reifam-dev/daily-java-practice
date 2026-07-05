import java.util.HashMap;
import java.util.ArrayList;

/**
 * Day 84 – Environment management concepts in Java: config loading, validation, key masking.
 * 1Z0-811 standard: private fields, this keyword, getters, HashMap, toString override.
 */
public class Day84ApiConfig {

    private String environment;
    private HashMap<String, String> config;
    private ArrayList<String> requiredKeys;

    public Day84ApiConfig(String environment) {
        this.environment = environment;
        this.config = new HashMap<>();
        this.requiredKeys = new ArrayList<>();
        this.requiredKeys.add("ANTHROPIC_API_KEY");
        this.requiredKeys.add("DATABASE_URL");
    }

    public String getEnvironment() { return this.environment; }
    public int getConfigSize() { return this.config.size(); }

    public void set(String key, String value) {
        this.config.put(key, value);
    }

    public String get(String key, String defaultValue) {
        return this.config.getOrDefault(key, defaultValue);
    }

    public boolean isDebug() {
        return this.config.getOrDefault("DEBUG", "false").equals("true");
    }

    public String getLogLevel() {
        return this.config.getOrDefault("LOG_LEVEL", "INFO").toUpperCase();
    }

    public String maskKey(String key) {
        if (key.length() < 8) return "***";
        return key.substring(0, 4) + "*".repeat(key.length() - 4);
    }

    public boolean validate() {
        ArrayList<String> missing = new ArrayList<>();
        for (String key : this.requiredKeys) {
            if (!this.config.containsKey(key)) missing.add(key);
        }
        if (!missing.isEmpty()) {
            throw new IllegalStateException("Missing required config keys: " + missing);
        }
        return true;
    }

    public void printConfig() {
        System.out.println("=== Configuration ===");
        System.out.printf("Environment : %s%n", this.environment);
        System.out.printf("Debug       : %s%n", isDebug());
        System.out.printf("Log level   : %s%n", getLogLevel());
        for (String key : this.config.keySet()) {
            String value = key.contains("KEY") || key.contains("URL")
                    ? maskKey(this.config.get(key))
                    : this.config.get(key);
            System.out.printf("%-20s: %s%n", key, value);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "ApiConfig | env=%s | keys=%d | debug=%s",
                this.environment, this.config.size(), isDebug()
        );
    }

    public static void main(String[] args) {
        Day84ApiConfig config = new Day84ApiConfig("development");
        config.set("ANTHROPIC_API_KEY", "sk-ant-abc123xyz");
        config.set("DATABASE_URL", "postgresql://localhost:5432/property_db");
        config.set("DEBUG", "true");
        config.set("LOG_LEVEL", "debug");

        config.printConfig();
        System.out.println();
        System.out.println("Valid: " + config.validate());
        System.out.println(config);
    }
}