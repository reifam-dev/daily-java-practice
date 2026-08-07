import java.util.ArrayList;
import java.util.List;

class DealRecord {
    private String dealName;
    private Object marketValue;

    public DealRecord(String dealName, Object marketValue) {
        this.dealName = dealName;
        this.marketValue = marketValue;
    }

    public String getDealName() {
        return dealName;
    }

    public Object getMarketValue() {
        return marketValue;
    }
}

public class Day119ErrorQuiz {

    private static final int MAX_ATTEMPTS = 3;
    private static List<DealRecord> deadLetterQueue = new ArrayList<>();

    private static double processRecord(DealRecord record) {
        return (double) record.getMarketValue() * 1.0;
    }

    private static void processWithDlq(DealRecord record) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            try {
                double result = processRecord(record);
                System.out.println("Processed: " + record.getDealName() + " -> " + result);
                return;
            } catch (ClassCastException e) {
                attempts++
            }
        }
        deadLetterQueue.add(record);
    }

    public static void main(String[] args) {
        List<DealRecord> records = new ArrayList<>();
        records.add(new DealRecord("Riverside JV", 12500000.0));
        records.add(new DealRecord("Bad Record", "not_a_number"));
        records.add(new DealRecord("Logistics Portfolio", 34200000.0));

        for (DealRecord record : records) {
            processWithDlq(record);
        }

        System.out.println("Dead letter queue size: " + deadLetterQueue.size());
    }
}