import java.util.HashMap;
import java.util.Map;

public class Day135ErrorQuiz {

    private static Map<String, Double> database = new HashMap<>();
    private static Map<String, Double> cache = new HashMap<>();

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
        return value;
    }

    private static void updateDeal(String dealId, double newMarketValue) {
        database.put(dealId, newMarketValue)
    }

    public static void main(String[] args) {
        System.out.println(getDeal("deal-1"));
        System.out.println(getDeal("deal-1"));

        updateDeal("deal-1", 13000000.0);
        System.out.println(getDeal("deal-1"));
    }
}