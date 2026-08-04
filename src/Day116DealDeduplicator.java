import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class DealRecord {
    private final String dealName;
    private final double marketValue;

    public DealRecord(String dealName, double marketValue) {
        this.dealName = dealName;
        this.marketValue = marketValue;
    }

    public String getDealName() {
        return this.dealName;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    @Override
    public String toString() {
        return "DealRecord{name='" + this.dealName + "', marketValue=" + this.marketValue + '}';
    }
}

/**
 * Removes near-duplicate deal records arriving with inconsistent
 * formatting (case, trailing whitespace) from different sources,
 * keeping the first occurrence of each normalised key.
 */
public class Day116DealDeduplicator {

    private static String normaliseKey(String dealName) {
        return dealName.trim().toLowerCase();
    }

    private static List<DealRecord> deduplicate(List<DealRecord> records) {
        Set<String> seenKeys = new HashSet<>();
        List<DealRecord> deduplicated = new ArrayList<>();

        for (DealRecord record : records) {
            String key = normaliseKey(record.getDealName());
            if (seenKeys.contains(key)) {
                continue;
            }
            seenKeys.add(key);
            deduplicated.add(record);
        }
        return deduplicated;
    }

    public static void main(String[] args) {
        List<DealRecord> records = new ArrayList<>();
        records.add(new DealRecord("Riverside JV", 12500000.0));
        records.add(new DealRecord("riverside jv", 12500000.0));
        records.add(new DealRecord("Westgate Retail ", 8100000.0));
        records.add(new DealRecord("Westgate Retail", 8100000.0));

        List<DealRecord> result = deduplicate(records);
        System.out.println("Original: " + records.size() + ", Deduplicated: " + result.size());
        for (DealRecord record : result) {
            System.out.println("  " + record);
        }
    }
}