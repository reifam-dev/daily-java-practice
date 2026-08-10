import java.util.HashMap;
import java.util.Map;

class NoFallbackAvailableException extends RuntimeException {
    public NoFallbackAvailableException(String message) {
        super(message);
    }
}

public class Day122ErrorQuiz {

    private static Map<String, Double> lastKnownGood = new HashMap<>();

    private static double fetchLiveValuation(String dealName) {
        if (dealName.equals("Riverside JV")) {
            throw new RuntimeException("Valuation service unavailable");
        }
        return 12500000.0;
    }

    private static Map<String, Object> getValuation(String dealName) {
        double value = fetchLiveValuation(dealName);
        lastKnownGood.put(dealName, value);
        Map<String, Object> result = new HashMap<>();
        result.put("dealName", dealName);
        result.put("value", value);
        result.put("source", "live");
        return result;
    }

    private static Map<String, Object> getValuationWithFallback(String dealName) {
        try {
            return getValuation(dealName);
        } catch (RuntimeException e) {
            double cachedValue = lastKnownGood.get(dealName);
            Map<String, Object> result = new HashMap<>();
            result.put("dealName", dealName);
            result.put("value", cachedValue);
            result.put("source", "cached");
            return result
        }
    }

    public static void main(String[] args) {
        lastKnownGood.put("Riverside JV", 12000000.0);
        System.out.println(getValuationWithFallback("Riverside JV"));
        System.out.println(getValuationWithFallback("Westgate Retail"));
    }
}