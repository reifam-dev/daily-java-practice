// This file contains 3 deliberate bugs. Find and fix them.
import java.util.HashMap;

public class Day84ErrorQuiz {

    private String environment;
    private HashMap<String, String> config;

    public Day84ErrorQuiz(String environment) {
        environment = environment;              // Bug 1: missing this
        this.config = new HashMap<>();
    }

    public void set(String key, String value) {
        this.config.put(key, value);
    }

    public String get(String key, String defaultValue) {
        return this.config.getOrDefault(key, defaultValue);
    }

    public boolean isDebug() {
        return this.config.getOrDefault("DEBUG", "false") == "true";  // Bug 2: == should be .equals()
    }

    public String maskKey(String key) {
        if (key.length() < 8) return "***";
        return key.substring(0, 4) + "*".repeat(key.length() - 4)
    }                                           // Bug 3: missing semicolon

    @Override
    public String toString() {
        return "ApiConfig | env=" + environment + " | keys=" + config.size();
    }

    public static void main(String[] args) {
        Day84ErrorQuiz config = new Day84ErrorQuiz("development");
        config.set("ANTHROPIC_API_KEY", "sk-ant-abc123xyz");
        config.set("DATABASE_URL", "postgresql://localhost:5432/db");
        config.set("DEBUG", "true");
        System.out.println(config);
        System.out.println("Debug: " + config.isDebug());
        System.out.println("Masked: " + config.maskKey("sk-ant-abc123xyz"));
    }
}