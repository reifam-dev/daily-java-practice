import java.util.ArrayList;
import java.util.List;

/**
 * Allows bursts up to capacity, refilling continuously over time, and
 * caps refill at the bucket's capacity so idle time can't accumulate
 * an unbounded surplus.
 */
final class TokenBucketLimiter {
    private final int capacity;
    private final double refillRate;
    private double tokens;
    private long lastRefillMillis;

    public TokenBucketLimiter(int capacity, double refillRatePerSecond) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
        this.refillRate = refillRatePerSecond;
        this.tokens = capacity;
        this.lastRefillMillis = System.currentTimeMillis();
    }

    private void refill() {
        long now = System.currentTimeMillis();
        double elapsedSeconds = (now - this.lastRefillMillis) / 1000.0;
        double added = elapsedSeconds * this.refillRate;
        this.tokens = Math.min(this.capacity, this.tokens + added);
        this.lastRefillMillis = now;
    }

    public boolean allowRequest() {
        refill();
        if (this.tokens >= 1) {
            this.tokens = this.tokens - 1;
            return true;
        }
        return false;
    }
}

/**
 * Only counts requests within the last windowSeconds against the
 * limit, giving a smoother, more precise limit than a fixed window.
 */
final class SlidingWindowLimiter {
    private final int maxRequests;
    private final double windowSeconds;
    private final List<Long> requestTimes;

    public SlidingWindowLimiter(int maxRequests, double windowSeconds) {
        this.maxRequests = maxRequests;
        this.windowSeconds = windowSeconds;
        this.requestTimes = new ArrayList<>();
    }

    public boolean allowRequest() {
        long now = System.currentTimeMillis();
        double windowMillis = this.windowSeconds * 1000.0;
        this.requestTimes.removeIf(t -> now - t >= windowMillis);

        if (this.requestTimes.size() < this.maxRequests) {
            this.requestTimes.add(now);
            return true;
        }
        return false;
    }
}

public class Day144RateLimiters {

    public static void main(String[] args) {
        TokenBucketLimiter bucket = new TokenBucketLimiter(3, 1.0);
        for (int i = 0; i < 5; i++) {
            System.out.println("Token bucket request " + i + ": " + bucket.allowRequest());
        }

        SlidingWindowLimiter window = new SlidingWindowLimiter(3, 10.0);
        for (int i = 0; i < 5; i++) {
            System.out.println("Sliding window request " + i + ": " + window.allowRequest());
        }
    }
}