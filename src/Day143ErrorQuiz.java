import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day143ErrorQuiz {

    private static Map<String, Double> dealLedger = new HashMap<>();
    private static List<String> processedMessageIds = new ArrayList<>();

    private static String processDealMessage(String messageId, String dealId, double marketValue) {
        if (processedMessageIds.contains(messageId)) {
            return "skipped_duplicate";
        }

        double current = dealLedger.getOrDefault(dealId, 0.0);
        dealLedger.put(dealId, current + marketValue)

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
    }
}