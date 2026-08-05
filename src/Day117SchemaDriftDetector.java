import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Scans a batch of records against an expected field set and type,
 * flagging missing fields and wrong types without crashing on any
 * single malformed record - a Java analogue of the Python schema
 * drift detector.
 */
public class Day117SchemaDriftDetector {

    private static List<String> checkRecord(Map<String, Object> record) {
        List<String> issues = new ArrayList<>();

        if (!record.containsKey("dealName")) {
            issues.add("missing field: dealName");
        } else if (!(record.get("dealName") instanceof String)) {
            issues.add("wrong type for dealName");
        }

        if (!record.containsKey("marketValue")) {
            issues.add("missing field: marketValue");
        } else if (!(record.get("marketValue") instanceof Double)) {
            issues.add("wrong type for marketValue");
        }

        return issues;
    }

    public static void main(String[] args) {
        List<Map<String, Object>> batch = new ArrayList<>();

        Map<String, Object> record1 = new HashMap<>();
        record1.put("dealName", "Riverside JV");
        record1.put("marketValue", 12500000.0);
        batch.add(record1);

        Map<String, Object> record2 = new HashMap<>();
        record2.put("dealName", "Logistics Portfolio");
        record2.put("marketValue", "34200000");
        batch.add(record2);

        boolean driftFound = false;
        for (int i = 0; i < batch.size(); i++) {
            List<String> issues = checkRecord(batch.get(i));
            if (!issues.isEmpty()) {
                driftFound = true;
                System.out.println("Record " + i + ": " + issues);
            }
        }
        if (!driftFound) {
            System.out.println("No schema drift detected.");
        }
    }
}