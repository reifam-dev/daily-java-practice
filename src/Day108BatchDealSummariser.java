import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class BatchRequest {
    private final String customId;
    private final String dealText;

    public BatchRequest(String customId, String dealText) {
        this.customId = customId;
        this.dealText = dealText;
    }

    public String getCustomId() {
        return this.customId;
    }

    public String getDealText() {
        return this.dealText;
    }

    @Override
    public String toString() {
        return "BatchRequest{customId='" + this.customId + "', dealText='" + this.dealText + "'}";
    }
}

/**
 * Simulates batch-style processing: builds one request per deal,
 * each tagged with a stable custom_id, and maps results back by
 * that id - a Java analogue of the Anthropic Message Batches API
 * pattern used in the Python clean file.
 */
public class Day108BatchDealSummariser {

    private static List<BatchRequest> buildBatchRequests(String[] deals) {
        List<BatchRequest> requests = new ArrayList<>();
        for (int i = 0; i < deals.length; i++) {
            requests.add(new BatchRequest("deal-" + i, deals[i]));
        }
        return requests;
    }

    private static Map<String, String> runBatch(String[] deals) {
        List<BatchRequest> requests = buildBatchRequests(deals);
        Map<String, String> results = new LinkedHashMap<>();
        for (BatchRequest request : requests) {
            String summary = "Summary of: " + request.getDealText();
            results.put(request.getCustomId(), summary);
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