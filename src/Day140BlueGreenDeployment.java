import java.util.HashMap;
import java.util.Map;

final class DeploymentSwitchException extends RuntimeException {
    public DeploymentSwitchException(String message) {
        super(message);
    }
}

/**
 * Maintains two identical environments, blue and green. A new version
 * deploys to whichever is currently inactive, is health-checked, and
 * traffic is switched to it only if healthy - with rollback as an
 * instant switch back, not a redeploy.
 */
public class Day140BlueGreenDeployment {

    private final Map<String, String> environments;
    private String active;
    private String previous;

    public Day140BlueGreenDeployment() {
        this.environments = new HashMap<>();
        this.environments.put("blue", "v1.0");
        this.environments.put("green", null);
        this.active = "blue";
        this.previous = null;
    }

    private String other(String environment) {
        return environment.equals("blue") ? "green" : "blue";
    }

    public String deployToInactive(String version) {
        String inactive = other(this.active);
        this.environments.put(inactive, version);
        return inactive;
    }

    public boolean healthCheck(String environment) {
        return this.environments.get(environment) != null;
    }

    public void switchTraffic(String targetEnvironment) {
        if (!healthCheck(targetEnvironment)) {
            throw new DeploymentSwitchException(
                    "Cannot switch: " + targetEnvironment + " failed health check");
        }
        this.previous = this.active;
        this.active = targetEnvironment;
    }

    public void rollback() {
        if (this.previous == null) {
            throw new DeploymentSwitchException("No previous environment to roll back to");
        }
        this.active = this.previous;
        this.previous = null;
    }

    public static void main(String[] args) {
        Day140BlueGreenDeployment manager = new Day140BlueGreenDeployment();
        System.out.println("Active: " + manager.active + " " + manager.environments.get(manager.active));

        String inactiveEnv = manager.deployToInactive("v2.0");
        System.out.println("Deployed v2.0 to " + inactiveEnv);

        manager.switchTraffic(inactiveEnv);
        System.out.println("Active: " + manager.active + " " + manager.environments.get(manager.active));

        manager.rollback();
        System.out.println("After rollback, active: " + manager.active + " "
                + manager.environments.get(manager.active));
    }
}