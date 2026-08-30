import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class UnsupportedVersionException extends RuntimeException {
    public UnsupportedVersionException(String message) {
        super(message);
    }
}

/**
 * Serves a resource across multiple explicit, coexisting API
 * versions, warns callers using a deprecated version, and fails
 * clearly when an unknown version is requested.
 */
public class Day142VersionedDealApi {

    private static final Set<String> DEPRECATED_VERSIONS = Set.of("v1");
    private static final Set<String> SUPPORTED_VERSIONS = Set.of("v1", "v2");

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
        if (!SUPPORTED_VERSIONS.contains(version)) {
            throw new UnsupportedVersionException("API version '" + version + "' is not supported");
        }

        if (DEPRECATED_VERSIONS.contains(version)) {
            System.out.println("Warning: " + version + " is deprecated");
        }

        if (version.equals("v1")) {
            return getDealV1(dealId);
        }
        return getDealV2(dealId);
    }

    public static void main(String[] args) {
        Day142VersionedDealApi api = new Day142VersionedDealApi();

        System.out.println(api.handleRequest("deal-1", "v1"));
        System.out.println(api.handleRequest("deal-1", "v2"));

        try {
            System.out.println(api.handleRequest("deal-1", "v3"));
        } catch (UnsupportedVersionException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}