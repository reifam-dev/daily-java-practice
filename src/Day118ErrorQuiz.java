class CircuitOpenException extends RuntimeException {
    public CircuitOpenException(String message) {
        super(message);
    }
}

public class Day118ErrorQuiz {

    private final int failureThreshold;
    private final long cooldownMillis;
    private int failureCount;
    private String state;
    private long openedAt;

    public Day118ErrorQuiz(int failureThreshold, long cooldownMillis) {
        this.failureThreshold = failureThreshold;
        this.cooldownMillis = cooldownMillis;
        this.failureCount = 0;
        this.state = "closed";
    }

    private boolean shouldAttemptReset() {
        return System.currentTimeMillis() - openedAt > cooldownMillis;
    }

    public String call(boolean shouldFail) {
        if (state.equals("open")) {
            if (shouldAttemptReset()) {
                state = "half_open";
            } else {
                throw new CircuitOpenException("Circuit is open - call rejected");
            }
        }

        if (shouldFail) {
            failureCount++;
            if (failureCount >= failureThreshold) {
                state = "open"
            }
            throw new RuntimeException("Service unavailable");
        }

        state = "closed";
        return "Service response OK";
    }

    public static void main(String[] args) {
        Day118ErrorQuiz breaker = new Day118ErrorQuiz(2, 5000);
        for (int attempt = 0; attempt < 4; attempt++) {
            try {
                System.out.println(breaker.call(true));
            } catch (RuntimeException e) {
                System.out.println("Attempt " + attempt + ": " + e.getMessage());
            }
        }
    }
}