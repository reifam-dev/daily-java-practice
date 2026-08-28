import java.util.HashMap;
import java.util.Map;

public class Day140ErrorQuiz {

    private Map<String, String> environments = new HashMap<>();
    private String active;

    public Day140ErrorQuiz() {
        environments.put("blue", "v1.0");
        environments.put("green", null);
        active = "blue";
    }

    public String deployToInactive(String version) {
        String inactive = active.equals("blue") ? "blue" : "green";
        environments.put(inactive, version);
        return inactive;
    }

    public boolean healthCheck(String environment) {
        return environments.get(environment) != null;
    }

    public void switchTraffic(String targetEnvironment) {
        if (!healthCheck(targetEnvironment)) {
            throw new RuntimeException("Cannot switch: " + targetEnvironment + " failed health check");
        }
        active = targetEnvironment
    }

    public static void main(String[] args) {
        Day140ErrorQuiz manager = new Day140ErrorQuiz();
        System.out.println("Active: " + manager.active + " " + manager.environments.get(manager.active));

        String inactiveEnv = manager.deployToInactive("v2.0");
        System.out.println("Deployed v2.0 to " + inactiveEnv);

        manager.switchTraffic(inactiveEnv);
        System.out.println("Active: " + manager.active + " " + manager.environments.get(manager.active));
    }
}