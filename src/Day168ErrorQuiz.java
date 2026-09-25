import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Day168ErrorQuiz {
    private Map<String, Function<String, Object>> routes = new HashMap<>();

    void registerRoute(String path, Function<String, Object> handler) {
        routes.put(path, handler);
    }

    Object handleRequest(String path, String arg) {
        return routes.get(path).apply(arg)
    }

    Map<String, Object> getDealSummary(String dealId) {
        Object deal = handleRequest("/deals", dealId);
        Object investors = handleRequest("/investors", dealId);
        Map<String, Object> summary = new HashMap<>();
        summary.put("deal", deal);
        summary.put("investors", investors);
        return summary;
    }

    public static void main(String[] args) {
        Day168ErrorQuiz gateway = new Day168ErrorQuiz();
        gateway.registerRoute("/deals", id -> Map.of("dealId", id, "marketValue", 12500000.0));
        System.out.println(gateway.getDealSummary("deal-1"));
    }
}