import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

final class DealResult {
    private final int id;
    private final String dealName;
    private final double marketValue;

    public DealResult(int id, String dealName, double marketValue) {
        this.id = id;
        this.dealName = dealName;
        this.marketValue = marketValue;
    }

    public String getDealName() {
        return this.dealName;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    @Override
    public String toString() {
        return "DealResult{id=" + this.id + ", dealName='" + this.dealName
                + "', marketValue=" + this.marketValue + '}';
    }
}

final class IdempotencyKeyConflictException extends RuntimeException {
    public IdempotencyKeyConflictException(String message) {
        super(message);
    }
}

/**
 * Uses a client-supplied idempotency key to detect and safely handle
 * a duplicate request, returning the original response rather than
 * creating a second deal.
 */
public class Day129IdempotentDealApi {

    private final Map<String, DealResult> processedRequests;

    public Day129IdempotentDealApi() {
        this.processedRequests = new HashMap<>();
    }

    public DealResult createDeal(String idempotencyKey, String dealName, double marketValue) {
        if (this.processedRequests.containsKey(idempotencyKey)) {
            DealResult cached = this.processedRequests.get(idempotencyKey);
            boolean sameData = cached.getDealName().equals(dealName)
                    && Objects.equals(cached.getMarketValue(), marketValue);
            if (!sameData) {
                throw new IdempotencyKeyConflictException(
                        "Key " + idempotencyKey + " was already used with different request data");
            }
            return cached;
        }

        int dealId = this.processedRequests.size() + 1;
        DealResult result = new DealResult(dealId, dealName, marketValue);
        this.processedRequests.put(idempotencyKey, result);
        return result;
    }

    public static void main(String[] args) {
        Day129IdempotentDealApi api = new Day129IdempotentDealApi();

        DealResult response1 = api.createDeal("key-abc-123", "Riverside JV", 12500000.0);
        System.out.println(response1);

        DealResult response2 = api.createDeal("key-abc-123", "Riverside JV", 12500000.0);
        System.out.println(response2);

        DealResult response3 = api.createDeal("key-xyz-789", "Westgate Retail", 8100000.0);
        System.out.println(response3);

        System.out.println("Total deals created: " + api.processedRequests.size());
    }
}