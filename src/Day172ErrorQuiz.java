import java.util.HashMap;
import java.util.Map;

public class Day172ErrorQuiz {
    private static final double TTL_SECONDS = 5.0;
    private static Map<String, Double> cache = new HashMap<>();
    private static Map<String, Long> timestamps = new HashMap<>();

    private static Double cacheGet(String key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        return null;
    }

    private static void cacheSet(String key, double value) {
        cache.put(key, value);
        timestamps.put(key, System.currentTimeMillis())
    }

    private static void invalidate(String key) {
        cache.remove(key);
    }

    public static void main(String[] args) {
        cacheSet("deal-1", 12500000.0);
        System.out.println(cacheGet("deal-1"));
        invalidate("deal-1");
        System.out.println(cacheGet("deal-1"));
    }
}