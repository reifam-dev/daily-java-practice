import java.util.concurrent.ThreadLocalRandom;

/**
 * Exponential backoff capped at maxDelay, then "full jitter" (random
 * between 0 and the capped delay) so many clients retrying
 * simultaneously don't all retry at exactly the same moment.
 */
public class Day171RetryJitter {
    private static double computeBackoffDelay(int attempt, double baseDelay, double maxDelay) {
        double delay = baseDelay * Math.pow(2, attempt);
        return Math.min(delay, maxDelay);
    }

    private static double computeBackoffWithJitter(int attempt, double baseDelay, double maxDelay) {
        double cappedDelay = computeBackoffDelay(attempt, baseDelay, maxDelay);
        return ThreadLocalRandom.current().nextDouble(0, cappedDelay);
    }

    public static void main(String[] args) {
        for (int attempt = 0; attempt < 6; attempt++) {
            double plain = computeBackoffDelay(attempt, 1.0, 30.0);
            double jittered = computeBackoffWithJitter(attempt, 1.0, 30.0);
            System.out.printf("attempt %d: plain=%.2fs, jittered=%.2fs%n", attempt, plain, jittered);
        }
    }
}