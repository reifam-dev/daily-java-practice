import java.util.HashMap;
import java.util.Map;

/**
 * Reads check the entry's TTL and treat an expired entry as a miss,
 * actively removing it too, rather than serving stale data.
 */
public class Day172TtlCacheInvalidation {
    private static final long TTL_MILLIS = 5000;
    private static final Map<String, Double> cache = new HashMap<>();
    private static final Map<String, Long> timestamps = new HashMap<>();

    private static Double cacheGet(String key) {
        if (!cache.containsKey(key)) {
            return null;
        }

        long age = System.currentTimeMillis() - timestamps.get(key);
        if (age > TTL_MILLIS) {
            invalidate(key);
            return null;
        }

        return cache.get(key);
    }

    private static void cacheSet(String key, double value) {
        cache.put(key, value);
        timestamps.put(key, System.currentTimeMillis());
    }

    private static void invalidate(String key) {
        cache.remove(key);
        timestamps.remove(key);
    }

    public static void main(String[] args) {
        cacheSet("deal-1", 12500000.0);
        System.out.println(cacheGet("deal-1"));
        invalidate("deal-1");
        System.out.println(cacheGet("deal-1"));
    }
}