import java.util.ArrayList;
import java.util.List;

class DealRecord {
    private String dealName;
    private double marketValue;

    public DealRecord(String dealName, double marketValue) {
        this.dealName = dealName;
        this.marketValue = marketValue;
    }

    public String getDealName() {
        return dealName;
    }

    public double getMarketValue() {
        return marketValue;
    }
}

public class Day116ErrorQuiz {

    private static String normaliseKey(String dealName) {
        return dealName.toLowerCase();
    }

    private static List<DealRecord> deduplicate(List<DealRecord> records) {
        List<String> seenKeys = new ArrayList<>();
        List<DealRecord> deduplicated = new ArrayList<>();

        for (DealRecord record : records) {
            String key = normaliseKey(record.getDealName());
            if (seenKeys.contains(key)) {
                continue;
            }
            deduplicated.add(record)
        }
        return deduplicated;
    }

    public static void main(String[] args) {
        List<DealRecord> records = new ArrayList<>();
        records.add(new DealRecord("Riverside JV", 12500000.0));
        records.add(new DealRecord("riverside jv", 12500000.0));
        records.add(new DealRecord("Westgate Retail ", 8100000.0));

        List<DealRecord> result = deduplicate(records);
        System.out.println("Original: " + records.size() + ", Deduplicated: " + result.size());
    }
}