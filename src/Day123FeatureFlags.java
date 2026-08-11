import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Percentage-based feature rollout, deterministic per user id via a
 * stable SHA-256 hash - a Java analogue of the Python FeatureFlags
 * class.
 */
public class Day123FeatureFlags {

    private final Map<String, Integer> flags;

    public Day123FeatureFlags() {
        this.flags = new HashMap<>();
    }

    public void setRollout(String flagName, int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("percentage must be between 0 and 100");
        }
        this.flags.put(flagName, percentage);
    }

    public boolean isEnabled(String flagName, String userId) throws NoSuchAlgorithmException {
        int percentage = this.flags.getOrDefault(flagName, 0);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(userId.getBytes());

        long unsignedValue = 0;
        for (int i = 0; i < 4; i++) {
            unsignedValue = (unsignedValue << 8) | (hash[i] & 0xFF);
        }
        int bucket = (int) (Math.abs(unsignedValue) % 100);

        return bucket < percentage;
    }

    private static double calculateDealScore(String dealName, boolean useNewAlgorithm) {
        if (useNewAlgorithm) {
            return dealName.length() * 1.5;
        }
        return dealName.length() * 1.0;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Day123FeatureFlags flags = new Day123FeatureFlags();
        flags.setRollout("new_scoring_algorithm", 50);

        String[] userIds = {"user-1", "user-2", "user-3", "user-4"};
        for (String userId : userIds) {
            boolean enabled = flags.isEnabled("new_scoring_algorithm", userId);
            boolean enabledAgain = flags.isEnabled("new_scoring_algorithm", userId);
            assert enabled == enabledAgain : "flag result must be deterministic per user";

            double score = calculateDealScore("Riverside JV", enabled);
            System.out.println(userId + ": enabled=" + enabled + ", score=" + score);
        }

        System.out.println("unregistered flag defaults to: "
                + flags.isEnabled("unknown_flag", "user-1"));
    }
}