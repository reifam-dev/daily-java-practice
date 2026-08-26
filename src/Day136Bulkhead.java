import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

final class BulkheadFullException extends RuntimeException {
    public BulkheadFullException(String message) {
        super(message);
    }
}

final class Bulkhead {
    private final String name;
    private final int maxConcurrent;
    private int inUse;

    public Bulkhead(String name, int maxConcurrent) {
        this.name = name;
        this.maxConcurrent = maxConcurrent;
        this.inUse = 0;
    }

    public boolean acquire() {
        if (this.inUse < this.maxConcurrent) {
            this.inUse++;
            return true;
        }
        return false;
    }

    public void release() {
        if (this.inUse > 0) {
            this.inUse--;
        }
    }
}

/**
 * Partitions concurrency capacity per downstream dependency, so a
 * saturated bulkhead for one service can't consume capacity another
 * service's bulkhead needs.
 */
public class Day136Bulkhead {

    private final Map<String, Bulkhead> bulkheads;

    public Day136Bulkhead() {
        this.bulkheads = new HashMap<>();
    }

    public void register(String name, int maxConcurrent) {
        this.bulkheads.put(name, new Bulkhead(name, maxConcurrent));
    }

    public <T> T callWithBulkhead(String name, Function<String, T> func, String arg) {
        Bulkhead bulkhead = this.bulkheads.get(name);
        if (!bulkhead.acquire()) {
            throw new BulkheadFullException("Bulkhead '" + name + "' is full");
        }
        try {
            return func.apply(arg);
        } finally {
            bulkhead.release();
        }
    }

    public static void main(String[] args) {
        Day136Bulkhead registry = new Day136Bulkhead();
        registry.register("valuation_service", 2);
        registry.register("pricing_service", 2);

        registry.bulkheads.get("valuation_service").acquire();
        registry.bulkheads.get("valuation_service").acquire();

        try {
            String result = registry.callWithBulkhead(
                    "valuation_service", d -> "Valuation for " + d, "Riverside JV");
            System.out.println(result);
        } catch (BulkheadFullException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        String pricingResult = registry.callWithBulkhead(
                "pricing_service", d -> "Price for " + d, "Westgate Retail");
        System.out.println(pricingResult);
    }
}