

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class DealEvent {
    private final String dealId;
    private final String eventType;
    private final Map<String, Object> data;

    public DealEvent(String dealId, String eventType, Map<String, Object> data) {
        this.dealId = dealId;
        this.eventType = eventType;
        this.data = data;
    }

    public String getDealId() {
        return this.dealId;
    }

    public String getEventType() {
        return this.eventType;
    }

    public Map<String, Object> getData() {
        return this.data;
    }
}

/**
 * Records every state change as an immutable event, deriving a
 * deal's current state on demand by replaying only that deal's
 * events in order.
 */
public class Day134DealEventStore {

    private static final List<DealEvent> eventLog = new ArrayList<>();

    private static void recordEvent(String dealId, String eventType, Map<String, Object> data) {
        eventLog.add(new DealEvent(dealId, eventType, data));
    }

    private static void createDeal(String dealId, String dealName, double marketValue) {
        Map<String, Object> data = new HashMap<>();
        data.put("dealName", dealName);
        data.put("marketValue", marketValue);
        recordEvent(dealId, "created", data);
    }
    private static void revalueDeal(String dealId, double newMarketValue) {

        Map<String, Object> data = new HashMap<>();
        data.put("marketValue", newMarketValue);
        recordEvent(dealId, "revalued", data);
    }

    private static Map<String, Object> rebuildDealState(String dealId) {
        Map<String, Object> state = new HashMap<>();
        for (DealEvent event : eventLog) {
            if (!event.getDealId().equals(dealId)) {
                continue;
            }
            if (event.getEventType().equals("created")) {
                state.put("dealName", event.getData().get("dealName"));
                state.put("marketValue", event.getData().get("marketValue"));
            } else if (event.getEventType().equals("revalued")) {
                state.put("marketValue", event.getData().get("marketValue"));
            }
        }
        return state;
    }

    private static List<DealEvent> getDealHistory(String dealId) {
        List<DealEvent> history = new ArrayList<>();
        for (DealEvent event : eventLog) {
            if (event.getDealId().equals(dealId)) {
                history.add(event);
            }
        }
        return history;
    }

    public static void main(String[] args) {
        createDeal("deal-1", "Riverside JV", 12500000.0);
        revalueDeal("deal-1", 13000000.0);
        createDeal("deal-2", "Westgate Retail", 8100000.0);
        revalueDeal("deal-1", 13500000.0);

        System.out.println("deal-1 state: " + rebuildDealState("deal-1"));
        System.out.println("deal-2 state: " + rebuildDealState("deal-2"));
        System.out.println("deal-1 history size: " + getDealHistory("deal-1").size());
    }
}