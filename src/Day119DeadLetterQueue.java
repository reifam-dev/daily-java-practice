import java.util.ArrayList;
import java.util.List;

final class DealRecord {
    private final String dealName;
    private final Object marketValue;

    public DealRecord(String dealName, Object marketValue) {
        this.dealName = dealName;
        this.marketValue = marketValue;
    }

    public String getDealName() {
        return this.dealName;
    }

    public Object getMarketValue() {
        return this.marketValue;
    }

    @Override
    public String toString() {
        return "DealRecord{name='" + this.dealName + "', marketValue=" + this.marketValue + '}';
    }
}

final class DeadLetterEntry {
    private final DealRecord record;
    private final String reason;

    public DeadLetterEntry(DealRecord record, String reason) {
        this.record = record;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return this.record + " - " + this.reason;
    }
}

/**
 * Retries processing a record up to a fixed number of attempts, then
 * routes it to a dead letter queue with a recorded failure reason if
 * every attempt fails.
 */
public class Day119DeadLetterQueue {

    private static final int MAX_ATTEMPTS = 3;
    private final List<DeadLetterEntry> deadLetterQueue;

    public Day119DeadLetterQueue() {
        this.deadLetterQueue = new ArrayList<>();
    }

    private double processRecord(DealRecord record) {
        return (double) record.getMarketValue() * 1.0;
    }

    public void processWithDlq(DealRecord record) {
        int attempts = 0;
        ClassCastException lastError = null;

        while (attempts < MAX_ATTEMPTS) {
            try {
                double result = processRecord(record);
                System.out.println("Processed: " + record.getDealName() + " -> " + result);
                return;
            } catch (ClassCastException e) {
                attempts++;
                lastError = e;
            }
        }
        this.deadLetterQueue.add(new DeadLetterEntry(record, String.valueOf(lastError)));
    }

    public int getDeadLetterQueueSize() {
        return this.deadLetterQueue.size();
    }

    public static void main(String[] args) {
        List<DealRecord> records = new ArrayList<>();
        records.add(new DealRecord("Riverside JV", 12500000.0));
        records.add(new DealRecord("Bad Record", "not_a_number"));
        records.add(new DealRecord("Logistics Portfolio", 34200000.0));

        Day119DeadLetterQueue processor = new Day119DeadLetterQueue();
        for (DealRecord record : records) {
            processor.processWithDlq(record);
        }

        System.out.println("Dead letter queue size: " + processor.getDeadLetterQueueSize());
    }
}