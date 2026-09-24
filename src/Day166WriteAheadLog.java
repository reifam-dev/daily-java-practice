import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class WalEntry {
    final String key;
    final double value;

    WalEntry(String key, double value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * The log entry is appended before the in-memory state update, so
 * replaying the log from the start reconstructs exactly what was
 * there before a crash, even after total loss of in-memory state.
 */
public class Day166WriteAheadLog {
    private static final Map<String, Double> dataStore = new HashMap<>();
    private static final List<WalEntry> wal = new ArrayList<>();

    private static void write(String key, double value) {
        wal.add(new WalEntry(key, value));
        dataStore.put(key, value);
    }

    private static Map<String, Double> crashAndRecover() {
        Map<String, Double> recovered = new HashMap<>();
        for (WalEntry entry : wal) {
            recovered.put(entry.key, entry.value);
        }
        return recovered;
    }

    public static void main(String[] args) {
        write("deal-1", 12500000.0);
        write("deal-2", 34200000.0);

        System.out.println("Live store: " + dataStore);
        dataStore.clear();
        System.out.println("After crash: " + dataStore);
        System.out.println("Recovered: " + crashAndRecover());
    }
}