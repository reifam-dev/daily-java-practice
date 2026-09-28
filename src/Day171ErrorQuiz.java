import java.util.concurrent.ThreadLocalRandom;

public class Day171ErrorQuiz {
    private static double computeBackoffDelay(int attempt, double baseDelay, double maxDelay) {
        double delay = baseDelay * Math.pow(2, attempt);
        return delay;
    }

    private static double computeBackoffWithJitter(int attempt, double baseDelay, double maxDelay) {
        double delay = computeBackoffDelay(attempt, baseDelay, maxDelay);
        return ThreadLocalRandom.current().nextDouble(0, delay)
    }

    public static void main(String[] args) {
        for (int attempt = 0; attempt < 6; attempt++) {
            double plain = computeBackoffDelay(attempt, 1.0, 30.0);
            double jittered = computeBackoffWithJitter(attempt, 1.0, 30.0);
            System.out.printf("attempt %d: plain=%.2fs, jittered=%.2fs%n", attempt, plain, jittered);
        }
    }
}