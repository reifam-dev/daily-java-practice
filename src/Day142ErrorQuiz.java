import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Day142ErrorQuiz {

    private Set<String> deprecatedVersions = Set.of("v1");

    private Map<String, Object> getDealV1(String dealId) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", dealId);
        result.put("value", 12500000.0);
        return result;
    }

    private Map<String, Object> getDealV2(String dealId) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", dealId);
        result.put("marketValue", 12500000.0);
        result.put("currency", "GBP");
        return result;
    }

    public Map<String, Object> handleRequest(String dealId, String version) {
        if (deprecatedVersions.contains(version)) {
            System.out.println("Warning: " + version + " is deprecated");
        }

        if (version.equals("v1")) {
            return getDealV1(dealId);
        }
        return getDealV2(dealId)
    }

    public static void main(String[] args) {
        Day142ErrorQuiz api = new Day142ErrorQuiz();

        System.out.println(api.handleRequest("deal-1", "v1"));
        System.out.println(api.handleRequest("deal-1", "v2"));
        System.out.println(api.handleRequest("deal-1", "v3"));
    }
}