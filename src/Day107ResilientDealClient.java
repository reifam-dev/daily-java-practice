final class RateLimitException extends RuntimeException {
    public RateLimitException(String message) {
        super(message);
    }
}

final class RetriesExhaustedException extends RuntimeException {
    public RetriesExhaustedException(String message) {
        super(message);
    }
}

/**
 * Simulates a resilient API client that retries a rate-limited call
 * with exponential backoff, mirroring the Python retry pattern.
 */
public class Day107ResilientDealClient {

    private static final int MAX_RETRIES = 3;
    private static final long BASE_DELAY_MILLIS = 1000;

    private int callCount;

    public Day107ResilientDealClient() {
        this.callCount = 0;
    }

    private String simulateApiCall() {
        this.callCount += 1;
        if (this.callCount < 3) {
            throw new RateLimitException("Rate limited");
        }
        return "Typical logistics LTV covenant sits around 60-65%.";
    }

    public String callWithRetry() throws InterruptedException {
        RateLimitException lastError = null;

        for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
            try {
                return simulateApiCall();
            } catch (RateLimitException e) {
                lastError = e;
                long delay = BASE_DELAY_MILLIS * (long) Math.pow(2, attempt);
                System.out.println("Rate limited, retrying in " + delay + "ms...");
                Thread.sleep(delay);
            }
        }

        throw new RetriesExhaustedException(
                "Failed after " + MAX_RETRIES + " attempts: " + lastError);
    }

    public int getCallCount() {
        return this.callCount;
    }

    public static void main(String[] args) throws InterruptedException {
        Day107ResilientDealClient client = new Day107ResilientDealClient();
        String result = client.callWithRetry();
        System.out.println(result);
        System.out.println("Total calls: " + client.getCallCount());
    }
}