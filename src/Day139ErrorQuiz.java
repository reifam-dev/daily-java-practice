import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day139ErrorQuiz {

    private static Map<String, Map<String, Object>> writeStore = new HashMap<>();
    private static Map<String, Map<String, Object>> readModel = new HashMap<>();

    private static void createDeal(String dealId, String dealName, double marketValue) {
        Map<String, Object> record = new HashMap<>();
        record.put("dealName", dealName);
        record.put("marketValue", marketValue);
        writeStore.put(dealId, record);
    }

    private static void updateDealValue(String dealId, double newMarketValue) {
        writeStore.get(dealId).put("marketValue", newMarketValue);
        readModel.get(dealId).put("dealName", writeStore.get(dealId).get("dealName"))
    }

    private static Map<String, Object> getDealSummary(String dealId) {
        return readModel.get(dealId);
    }

    private static List<Map<String, Object>> listAllSummaries() {
        List<Map<String, Object>> summaries = new ArrayList<>();
        for (String dealId : writeStore.keySet()) {
            summaries.add(writeStore.get(dealId));
        }
        return summaries;
    }

    public static void main(String[] args) {
        createDeal("deal-1", "Riverside JV", 12500000.0);
        System.out.println("summary: " + getDealSummary("deal-1"));

        updateDealValue("deal-1", 13000000.0);
        System.out.println("summary after update: " + getDealSummary("deal-1"));

        System.out.println("all summaries: " + listAllSummaries());
    }
}