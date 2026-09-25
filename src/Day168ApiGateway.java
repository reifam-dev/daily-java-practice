import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

final class UnknownRouteException extends RuntimeException {
    public UnknownRouteException(String message) { super(message); }
}

/**
 * A single entry point routes to and aggregates results from
 * multiple backend services, so a client makes one call instead of
 * coordinating several.
 */
public class Day168ApiGateway {
    private final Map<String, Function<String, Object>> routes = new HashMap<>();

    void registerRoute(String path, Function<String, Object> handler) {
        this.routes.put(path, handler);
    }

    Object handleRequest(String path, String arg) {
        if (!this.routes.containsKey(path)) {
            throw new UnknownRouteException("No route registered for " + path);
        }
        return this.routes.get(path).apply(arg);
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
        Day168ApiGateway gateway = new Day168ApiGateway();
        gateway.registerRoute("/deals", id -> Map.of("dealId", id, "marketValue", 12500000.0));
        gateway.registerRoute("/investors", id -> List.of("Fund A", "Fund B"));
        System.out.println(gateway.getDealSummary("deal-1"));
    }
}