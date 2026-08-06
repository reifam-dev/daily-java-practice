final class CircuitOpenException extends RuntimeException {
    public CircuitOpenException(String message) {
        super(message);
    }
}

/**
 * A simple three-state circuit breaker (closed, open, half-open) that
 * stops calling a failing dependency after enough consecutive
 * failures, then cautiously allows a trial call through once the
 * cooldown period has elapsed.
 */
public class Day118CircuitBreaker {

    private final int failureThreshold;
    private final long cooldownMillis;
    private int failureCount;
    private String state;
    private long openedAt;

    public Day118CircuitBreaker(int failureThreshold, long cooldownMillis) {
        this.failureThreshold = failureThreshold;
        this.cooldownMillis = cooldownMillis;
        this.failureCount = 0;
        this.state = "closed";
        this.openedAt = 0L;
    }

    private boolean shouldAttemptReset() {
        return System.currentTimeMillis() - this.openedAt > this.cooldownMillis;
    }

    public String call(boolean shouldFail) {
        if (this.state.equals("open")) {
            if (shouldAttemptReset()) {
                this.state = "half_open";
            } else {
                throw new CircuitOpenException("Circuit is open - call rejected");
            }
        }

        if (shouldFail) {
            this.failureCount++;
            if (this.failureCount >= this.failureThreshold) {
                this.state = "open";
                this.openedAt = System.currentTimeMillis();
            }
            throw new RuntimeException("Service unavailable");
        }

        this.failureCount = 0;
        this.state = "closed";
        return "Service response OK";
    }

    public String getState() {
        return this.state;
    }

    public static void main(String[] args) {
        Day118CircuitBreaker breaker = new Day118CircuitBreaker(2, 5000);
        for (int attempt = 0; attempt < 4; attempt++) {
            try {
                System.out.println(breaker.call(true));
            } catch (RuntimeException e) {
                System.out.println("Attempt " + attempt + ": " + e.getMessage()
                        + " (state=" + breaker.getState() + ")");
            }
        }
    }
}