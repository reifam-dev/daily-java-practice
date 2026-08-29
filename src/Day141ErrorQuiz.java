import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day141ErrorQuiz {

    private int canaryPercentage;
    private int errorCount;
    private int requestCount;

    public Day141ErrorQuiz(int canaryPercentage) {
        this.canaryPercentage = canaryPercentage;
        this.errorCount = 0;
        this.requestCount = 0;
    }

    private int bucketFor(String userId) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(userId.getBytes());
        return Math.abs(hash[0]) % 100;
    }

    public String routeRequest(String userId) throws NoSuchAlgorithmException {
        int bucket = bucketFor(userId);
        if (bucket < canaryPercentage) {
            return "canary";
        }
        return "stable";
    }

    public void recordResult(String version, boolean succeeded) {
        requestCount++;
        if (!succeeded) {
            errorCount++;
        }
    }

    public double canaryErrorRate() {
        return (double) errorCount / requestCount
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Day141ErrorQuiz canary = new Day141ErrorQuiz(10);

        for (int i = 0; i < 20; i++) {
            String userId = "user-" + i;
            String version = canary.routeRequest(userId);
            boolean succeeded = !(version.equals("canary") && i == 3);
            canary.recordResult(version, succeeded);
        }

        System.out.println("Canary requests: " + canary.requestCount + ", errors: " + canary.errorCount);
        System.out.println("Error rate: " + canary.canaryErrorRate());
    }
}