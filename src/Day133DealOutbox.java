import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Writes a deal and its corresponding "deal created" event together,
 * then publishes queued events separately, removing each only once
 * genuinely published - a Java analogue of the Python outbox pattern.
 */
public class Day133DealOutbox {

    private static final Map<String, String> deals = new HashMap<>();
    private static final List<Map<String, String>> outbox = new ArrayList<>();
    private static final List<Map<String, String>> publishedEvents = new ArrayList<>();

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
        Iterator<Map<String, String>> iterator = outbox.iterator();
        while (iterator.hasNext()) {
            Map<String, String> event = iterator.next();
            publishedEvents.add(event);
            iterator.remove();
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