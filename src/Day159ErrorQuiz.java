import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Day159ErrorQuiz {
    private Map<String, Consumer<Map<String, String>>> handlers = new HashMap<>();

    public void register(String eventType, Consumer<Map<String, String>> handler) {
        handlers.put(eventType, handler)
    }

    public void publish(String eventType, Map<String, String> payload) {
        handlers.get(eventType).accept(payload);
    }

    public static void main(String[] args) {
        Day159ErrorQuiz mediator = new Day159ErrorQuiz();
        mediator.register("deal_created", p -> System.out.println("Notifying: " + p.get("dealName")));
        mediator.register("deal_created", p -> System.out.println("Audit: " + p.get("dealName")));

        Map<String, String> payload = new HashMap<>();
        payload.put("dealName", "Riverside JV");
        mediator.publish("deal_created", payload);
    }
}