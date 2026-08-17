import java.util.HashMap;
import java.util.Map;

class DealResult {
    private int id;
    private String dealName;
    private double marketValue;

    public DealResult(int id, String dealName, double marketValue) {
        this.id = id;
        this.dealName = dealName;
        this.marketValue = marketValue;
    }

    @Override
    public String toString() {
        return "DealResult{id=" + id + ", dealName='" + dealName + "', marketValue=" + marketValue + "}";
    }
}

public class Day129ErrorQuiz {

    private static Map<String, DealResult> processedRequests = new HashMap<>();

    private static DealResult createDeal(String idempotencyKey, String dealName, double marketValue) {
        if (processedRequests.containsKey(idempotencyKey)) {
            return processedRequests.get(idempotencyKey);
        }

        int dealId = processedRequests.size() + 1;
        DealResult result = new DealResult(dealId, dealName, marketValue)
        return result;
    }

    public static void main(String[] args) {
        DealResult response1 = createDeal("key-abc-123", "Riverside JV", 12500000.0);
        System.out.println(response1);

        DealResult response2 = createDeal("key-abc-123", "Riverside JV", 12500000.0);
        System.out.println(response2);

        System.out.println("Total deals created: " + processedRequests.size());
    }
}