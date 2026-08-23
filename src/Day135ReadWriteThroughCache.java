import java.util.HashMap;
import java.util.Map;

/**
 * Read-through: a cache miss transparently fetches from the store and
 * populates the cache. Write-through: a write updates the store and
 * the cache together so they never drift apart.
 */
public class Day135ReadWriteThroughCache {

    private static final Map<String, Double> database = new HashMap<>();
    private static final Map<String, Double> cache = new HashMap<>();

    static {
        database.put("deal-1", 12500000.0);
    }

    private static Double getDeal(String dealId) {
        if (cache.containsKey(dealId)) {
            System.out.println("cache hit: " + dealId);
            return cache.get(dealId);
        }

        System.out.println("cache miss: " + dealId);
        Double value = database.get(dealId);
        if (value != null) {
            cache.put(dealId, value);
        }
        return value;
    }

    private static void updateDeal(String dealId, double newMarketValue) {
        if (!database.containsKey(dealId)) {
            throw new IllegalArgumentException("No such deal: " + dealId);
        }
        database.put(dealId, newMarketValue);
        cache.put(dealId, newMarketValue);
    }

    public static void main(String[] args) {
        System.out.println(getDeal("deal-1"));
        System.out.println(getDeal("deal-1"));

        updateDeal("deal-1", 13000000.0);
        System.out.println(getDeal("deal-1"));
    }
}