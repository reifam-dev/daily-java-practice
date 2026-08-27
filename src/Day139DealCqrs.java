import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Commands write through to both the write store and the read model.
 * Queries only ever read from the read model, never touching the
 * write store directly - a Java analogue of the Python CQRS split.
 */
public class Day139DealCqrs {

    private static final Map<String, Map<String, Object>> writeStore = new HashMap<>();
    private static final Map<String, Map<String, Object>> readModel = new HashMap<>();

    private static void createDeal(String dealId, String dealName, double marketValue) {
        Map<String, Object> record = new HashMap<>();
        record.put("dealName", dealName);
        record.put("marketValue", marketValue);
        writeStore.put(dealId, new HashMap<>(record));
        readModel.put(dealId, new HashMap<>(record));
    }

    private static void updateDealValue(String dealId, double newMarketValue) {
        writeStore.get(dealId).put("marketValue", newMarketValue);
        readModel.get(dealId).put("marketValue", newMarketValue);
    }

    private static Map<String, Object> getDealSummary(String dealId) {
        return readModel.get(dealId);
    }

    private static List<Map<String, Object>> listAllSummaries() {
        return new ArrayList<>(readModel.values());
    }

    public static void main(String[] args) {
        createDeal("deal-1", "Riverside JV", 12500000.0);
        System.out.println("summary: " + getDealSummary("deal-1"));

        updateDealValue("deal-1", 13000000.0);
        System.out.println("summary after update: " + getDealSummary("deal-1"));

        System.out.println("all summaries: " + listAllSummaries());
    }
}