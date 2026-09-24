import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WalEntry {
    String key;
    double value;

    WalEntry(String key, double value) {
        this.key = key;
        this.value = value;
    }
}

public class Day166ErrorQuiz {
    private static Map<String, Double> dataStore = new HashMap<>();
    private static List<WalEntry> wal = new ArrayList<>();

    private static void write(String key, double value) {
        dataStore.put(key, value);
    }

    private static Map<String, Double> crashAndRecover() {
        Map<String, Double> recovered = new HashMap<>();
        for (WalEntry entry : wal) {
            recovered.put(entry.key, entry.value)
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