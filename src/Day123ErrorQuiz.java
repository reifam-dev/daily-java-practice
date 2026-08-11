import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Day123ErrorQuiz {

    private Map<String, Integer> flags = new HashMap<>();

    public void setRollout(String flagName, int percentage) {
        flags.put(flagName, percentage);
    }

    public boolean isEnabled(String flagName, String userId) throws NoSuchAlgorithmException {
        int percentage = flags.get(flagName);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(userId.getBytes());
        int bucket = Math.abs(hash[0]) % 100;

        return bucket < percentage;
    }

    private static double calculateDealScore(String dealName, boolean useNewAlgorithm) {
        if (useNewAlgorithm) {
            return dealName.length() * 1.5;
        }
        return dealName.length() * 1.0;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Day123ErrorQuiz flags = new Day123ErrorQuiz();
        flags.setRollout("new_scoring_algorithm", 50);

        String[] userIds = {"user-1", "user-2", "user-3", "user-4"};
        for (String userId : userIds) {
            boolean enabled = flags.isEnabled("new_scoring_algorithm", userId);
            double score = calculateDealScore("Riverside JV", enabled);
            System.out.println(userId + ": enabled=" + enabled + ", score=" + score);
        }
    }
}