import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Tracks which message ids have already been applied, so a
 * redelivered duplicate from an at-least-once message queue is safely
 * skipped rather than double-counted into the ledger.
 */
public class Day143IdempotentDealConsumer {

    private static final Map<String, Double> dealLedger = new HashMap<>();
    private static final Set<String> processedMessageIds = new HashSet<>();

    private static String processDealMessage(String messageId, String dealId, double marketValue) {
        if (processedMessageIds.contains(messageId)) {
            return "skipped_duplicate";
        }

        double current = dealLedger.getOrDefault(dealId, 0.0);
        dealLedger.put(dealId, current + marketValue);
        processedMessageIds.add(messageId);

        return "processed";
    }

    public static void main(String[] args) {
        String[][] messages = {
                {"msg-1", "deal-1", "5000000.0"},
                {"msg-2", "deal-1", "3000000.0"},
                {"msg-1", "deal-1", "5000000.0"},
                {"msg-3", "deal-2", "2000000.0"}
        };

        for (String[] message : messages) {
            String outcome = processDealMessage(message[0], message[1], Double.parseDouble(message[2]));
            System.out.println(message[0] + ": " + outcome);
        }

        System.out.println("Ledger: " + dealLedger);
        assert dealLedger.get("deal-1") == 8000000.0 : "duplicate must not be double-counted";
    }
}