import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Routes a small, deterministic percentage of traffic to a canary
 * version, tracks its error rate separately from stable traffic, and
 * only recommends promotion if the observed error rate stays under a
 * configured threshold.
 */
public class Day141CanaryRelease {

    private final int canaryPercentage;
    private int errorCount;
    private int requestCount;

    public Day141CanaryRelease(int canaryPercentage) {
        if (canaryPercentage < 0 || canaryPercentage > 100) {
            throw new IllegalArgumentException("canaryPercentage must be between 0 and 100");
        }
        this.canaryPercentage = canaryPercentage;
        this.errorCount = 0;
        this.requestCount = 0;
    }

    private int bucketFor(String userId) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(userId.getBytes(StandardCharsets.UTF_8));

            long unsignedValue = 0;
            for (int i = 0; i < 4; i++) {
                unsignedValue = (unsignedValue << 8) | (hash[i] & 0xFF);
            }
            return (int) (Math.abs(unsignedValue) % 100);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }

    public String routeRequest(String userId) {
        int bucket = bucketFor(userId);
        if (bucket < this.canaryPercentage) {
            return "canary";
        }
        return "stable";
    }

    public void recordResult(String version, boolean succeeded) {
        if (!version.equals("canary")) {
            return;
        }
        this.requestCount++;
        if (!succeeded) {
            this.errorCount++;
        }
    }

    public double canaryErrorRate() {
        if (this.requestCount == 0) {
            return 0.0;
        }
        return (double) this.errorCount / this.requestCount;
    }

    public boolean shouldPromote(double maxErrorRate) {
        if (this.requestCount == 0) {
            return false;
        }
        return canaryErrorRate() < maxErrorRate;
    }

    public static void main(String[] args) {
        Day141CanaryRelease canary = new Day141CanaryRelease(10);

        for (int i = 0; i < 20; i++) {
            String userId = "user-" + i;
            String version = canary.routeRequest(userId);
            boolean succeeded = !(version.equals("canary") && i == 3);
            canary.recordResult(version, succeeded);
        }

        System.out.println("Canary requests: " + canary.requestCount + ", errors: " + canary.errorCount);
        System.out.println("Error rate: " + canary.canaryErrorRate());
        System.out.println("Should promote: " + canary.shouldPromote(0.05));
    }
}