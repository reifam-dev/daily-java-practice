import java.util.ArrayList;
import java.util.List;

class TokenBucketLimiter {
    private int capacity;
    private double refillRate;
    private double tokens;
    private long lastRefillMillis;

    public TokenBucketLimiter(int capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRate = refillRatePerSecond;
        this.tokens = capacity;
        this.lastRefillMillis = System.currentTimeMillis();
    }

    private void refill() {
        long now = System.currentTimeMillis();
        double elapsedSeconds = (now - lastRefillMillis) / 1000.0;
        double added = elapsedSeconds * refillRate;
        tokens = tokens + added;
        lastRefillMillis = now;
    }

    public boolean allowRequest() {
        refill();
        if (tokens >= 1) {
            tokens = tokens - 1
            return true;
        }
        return false;
    }
}

public class Day144ErrorQuiz {

    public static void main(String[] args) {
        TokenBucketLimiter bucket = new TokenBucketLimiter(3, 1.0);
        for (int i = 0; i < 5; i++) {
            System.out.println("Token bucket request " + i + ": " + bucket.allowRequest());
        }
    }
}