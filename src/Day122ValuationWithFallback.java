import java.util.HashMap;
import java.util.Map;

final class NoFallbackAvailableException extends RuntimeException {
    public NoFallbackAvailableException(String message) {
        super(message);
    }
}

/**
 * Attempts a live valuation lookup, falling back to the last known
 * good cached value if the live call fails, and only failing outright
 * if no cached value exists either.
 */
public class Day122ValuationWithFallback {

    private final Map<String, Double> lastKnownGood;

    public Day122ValuationWithFallback() {
        this.lastKnownGood = new HashMap<>();
    }

    private double fetchLiveValuation(String dealName) {
        if (dealName.equals("Riverside JV")) {
            throw new RuntimeException("Valuation service unavailable");
        }
        return 12500000.0;
    }

    private Map<String, Object> getValuation(String dealName) {
        double value = fetchLiveValuation(dealName);
        this.lastKnownGood.put(dealName, value);
        Map<String, Object> result = new HashMap<>();
        result.put("dealName", dealName);
        result.put("value", value);
        result.put("source", "live");
        return result;
    }

    public Map<String, Object> getValuationWithFallback(String dealName) {
        try {
            return getValuation(dealName);
        } catch (RuntimeException e) {
            if (!this.lastKnownGood.containsKey(dealName)) {
                throw new NoFallbackAvailableException(
                        "No cached value available for " + dealName);
            }
            double cachedValue = this.lastKnownGood.get(dealName);
            Map<String, Object> result = new HashMap<>();
            result.put("dealName", dealName);
            result.put("value", cachedValue);
            result.put("source", "cached");
            return result;
        }
    }

    public static void main(String[] args) {
        Day122ValuationWithFallback service = new Day122ValuationWithFallback();
        service.lastKnownGood.put("Riverside JV", 12000000.0);
        System.out.println(service.getValuationWithFallback("Riverside JV"));

        try {
            System.out.println(service.getValuationWithFallback("Westgate Retail"));
        } catch (NoFallbackAvailableException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }
}