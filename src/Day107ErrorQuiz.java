class RateLimitException extends RuntimeException {
    public RateLimitException(String message) {
        super(message);
    }
}

public class Day107ErrorQuiz {

    private static final int MAX_RETRIES = 3;
    private static final long BASE_DELAY_MILLIS = 1000;

    private static int callCount = 0;

    private static String simulateApiCall() {
        callCount = callCount + 1;
        if (callCount < 3) {
            throw new RateLimitException("Rate limited");
        }
        return "Typical logistics LTV covenant sits around 60-65%.";
    }

    private static String callWithRetry() throws InterruptedException {
        for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
            try {
                return simulateApiCall();
            } catch (RateLimitException e) {
                long delay = BASE_DELAY_MILLIS * attempt;
                System.out.println("Rate limited, retrying in " + delay + "ms...");
                Thread.sleep(delay)
            }
        }
        return "Failed after retries";
    }

    public static void main(String[] args) throws InterruptedException {
        String result = callWithRetry();
        System.out.println(result);
        System.out.println("Total calls: " + callCount)
    }
}