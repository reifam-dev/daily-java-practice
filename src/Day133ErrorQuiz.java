import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day133ErrorQuiz {

    private static Map<String, String> deals = new HashMap<>();
    private static List<Map<String, String>> outbox = new ArrayList<>();
    private static List<Map<String, String>> publishedEvents = new ArrayList<>();

    private static String createDeal(String dealName) {
        String dealId = "deal-" + (deals.size() + 1);
        deals.put(dealId, dealName);
        return dealId;
    }

    private static String createDealWithEvent(String dealName) {
        String dealId = createDeal(dealName);
        Map<String, String> event = new HashMap<>();
        event.put("type", "deal_created");
        event.put("dealId", dealId);
        outbox.add(event);
        return dealId;
    }

    private static int publishOutboxEvents() {
        int publishedCount = 0;
        for (Map<String, String> event : outbox) {
            publishedEvents.add(event);
            outbox.remove(event)
            publishedCount++;
        }
        return publishedCount;
    }

    public static void main(String[] args) {
        createDealWithEvent("Riverside JV");
        createDealWithEvent("Westgate Retail");

        System.out.println("Outbox size before publishing: " + outbox.size());
        int published = publishOutboxEvents();
        System.out.println("Published: " + published);
        System.out.println("Outbox size after publishing: " + outbox.size());
    }
}