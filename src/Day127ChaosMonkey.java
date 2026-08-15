import java.util.Random;

/**
 * Injects failures at a configured probability, seedable for
 * reproducibility, so calling code's failure-handling can actually be
 * exercised and verified rather than assumed.
 */
final class ChaosMonkey {
    private final double failureRate;
    private final Random random;

    public ChaosMonkey(double failureRate, long seed) {
        if (failureRate < 0.0 || failureRate > 1.0) {
            throw new IllegalArgumentException("failureRate must be between 0.0 and 1.0");
        }
        this.failureRate = failureRate;
        this.random = new Random(seed);
    }

    public void maybeFail(String operationName) {
        if (this.random.nextDouble() < this.failureRate) {
            throw new RuntimeException("Chaos-injected failure in: " + operationName);
        }
    }
}

public class Day127ChaosMonkey {

    private static void fetchDealWithChaos(String dealName, ChaosMonkey chaos) {
        chaos.maybeFail("fetch_deal");
    }

    public static void main(String[] args) {
        ChaosMonkey chaos = new ChaosMonkey(0.3, 42);
        int successes = 0;
        int failures = 0;

        for (int i = 0; i < 100; i++) {
            try {
                fetchDealWithChaos("Riverside JV", chaos);
                successes++;
            } catch (RuntimeException e) {
                failures++;
            }
        }

        System.out.println("successes=" + successes + ", failures=" + failures);
        double observedRate = (double) failures / (successes + failures);
        System.out.println("Observed failure rate: " + observedRate);
    }
}