import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class Bulkhead {
    private String name;
    private int maxConcurrent;
    private int inUse;

    public Bulkhead(String name, int maxConcurrent) {
        this.name = name;
        this.maxConcurrent = maxConcurrent;
        this.inUse = 0;
    }

    public boolean acquire() {
        if (inUse < maxConcurrent) {
            inUse++;
            return true;
        }
        return false;
    }

    public void release() {
        inUse++
    }
}

public class Day136ErrorQuiz {

    private Map<String, Bulkhead> bulkheads = new HashMap<>();

    public void register(String name, int maxConcurrent) {
        bulkheads.put(name, new Bulkhead(name, maxConcurrent));
    }

    public <T> T callWithBulkhead(String name, Function<String, T> func, String arg) {
        Bulkhead bulkhead = bulkheads.get(name);
        if (!bulkhead.acquire()) {
            throw new RuntimeException("Bulkhead '" + name + "' is full");
        }
        T result = func.apply(arg);
        bulkhead.release();
        return result;
    }

    public static void main(String[] args) {
        Day136ErrorQuiz registry = new Day136ErrorQuiz();
        registry.register("valuation_service", 2);

        registry.bulkheads.get("valuation_service").acquire();
        registry.bulkheads.get("valuation_service").acquire();

        try {
            String result = registry.callWithBulkhead(
                    "valuation_service", d -> "Valuation for " + d, "Riverside JV");
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}