import java.util.Random;

class ChaosMonkey {
    private double failureRate;
    private Random random;

    public ChaosMonkey(double failureRate, long seed) {
        this.failureRate = failureRate;
        this.random = new Random(seed);
    }

    public void maybeFail(String operationName) {
        if (random.nextDouble() < failureRate) {
            throw new RuntimeException("Chaos-injected failure in: " + operationName);
        }
    }
}

public class Day127ErrorQuiz {

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
                failures++
            }
        }

        System.out.println("successes=" + successes + ", failures=" + failures);
        double observedRate = failures / successes;
        System.out.println("Observed failure rate: " + observedRate);
    }
}