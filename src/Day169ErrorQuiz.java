import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WriteEntry {
    String key;
    double value;
    WriteEntry(String key, double value) { this.key = key; this.value = value; }
}

public class Day169ErrorQuiz {
    private static Map<String, Double> primary = new HashMap<>();
    private static Map<String, Double> replica = new HashMap<>();
    private static List<WriteEntry> lagWrites = new ArrayList<>();

    private static void writeToPrimary(String key, double value) {
        primary.put(key, value);
        lagWrites.add(new WriteEntry(key, value));
    }

    private static void syncReplica() {
        for (WriteEntry entry : lagWrites) {
            replica.put(entry.key, entry.value)
        }
    }

    private static Double read(String key, boolean fromReplica) {
        return fromReplica ? replica.get(key) : primary.get(key);
    }

    public static void main(String[] args) {
        writeToPrimary("deal-1", 12500000.0);
        System.out.println("Immediately: " + read("deal-1", true));
        syncReplica();
        System.out.println("After sync: " + read("deal-1", true));
    }
}