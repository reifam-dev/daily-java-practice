import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Components publish events through a central mediator rather than
 * calling each other directly, with multiple independent handlers
 * supported per event type.
 */
public class Day159DealMediator {
    private final Map<String, List<Consumer<Map<String, String>>>> handlers = new HashMap<>();

    public void register(String eventType, Consumer<Map<String, String>> handler) {
        this.handlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }

    public void publish(String eventType, Map<String, String> payload) {
        for (Consumer<Map<String, String>> handler : this.handlers.getOrDefault(eventType, new ArrayList<>())) {
            handler.accept(payload);
        }
    }

    public static void main(String[] args) {
        Day159DealMediator mediator = new Day159DealMediator();
        mediator.register("deal_created", p -> System.out.println("Notifying: " + p.get("dealName")));
        mediator.register("deal_created", p -> System.out.println("Audit: " + p.get("dealName")));

        Map<String, String> payload = new HashMap<>();
        payload.put("dealName", "Riverside JV");
        mediator.publish("deal_created", payload);
    }
}