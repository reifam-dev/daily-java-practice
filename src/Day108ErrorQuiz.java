import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BatchRequest {
    private String customId;
    private String dealText;

    public BatchRequest(String customId, String dealText) {
        this.customId = customId;
        dealText = dealText;
    }

    public String getCustomId() {
        return customId;
    }

    public String getDealText() {
        return dealText;
    }
}

public class Day108ErrorQuiz {

    private static List<BatchRequest> buildBatchRequests(String[] deals) {
        List<BatchRequest> requests = new ArrayList<>();
        for (int i = 0; i < deals.length; i++) {
            requests.add(new BatchRequest("deal-" + i, deals[i]))
        }
        return requests;
    }

    private static Map<String, String> runBatch(String[] deals) {
        List<BatchRequest> requests = buildBatchRequests(deals);
        Map<String, String> results = new HashMap<>();
        for (BatchRequest request : requests) {
            String summary = "Summary of: " + request.getDealText();
            results.put(request.getCustomId, summary);
        }
        return results;
    }

    public static void main(String[] args) {
        String[] deals = {
                "Riverside JV, 12.5m, logistics, 60% LTV.",
                "Westgate Retail, 8.1m, retail, 65% LTV."
        };
        Map<String, String> outcomes = runBatch(deals);
        for (Map.Entry<String, String> entry : outcomes.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}