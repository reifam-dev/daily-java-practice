import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class WriteEntry {
    final String key;
    final double value;
    WriteEntry(String key, double value) { this.key = key; this.value = value; }
}

/**
 * Reads default to the replica for scalability, but the primary can
 * be read directly right after a write for guaranteed
 * read-your-writes consistency, since replica sync is asynchronous.
 */
public class Day169ReplicaConsistency {
    private static final Map<String, Double> primary = new HashMap<>();
    private static final Map<String, Double> replica = new HashMap<>();
    private static final List<WriteEntry> lagWrites = new ArrayList<>();

    private static void writeToPrimary(String key, double value) {
        primary.put(key, value);
        lagWrites.add(new WriteEntry(key, value));
    }

    private static void syncReplica() {
        for (WriteEntry entry : lagWrites) {
            replica.put(entry.key, entry.value);
        }
        lagWrites.clear();
    }

    private static Double read(String key, boolean fromReplica) {
        return fromReplica ? replica.get(key) : primary.get(key);
    }

    public static void main(String[] args) {
        writeToPrimary("deal-1", 12500000.0);
        System.out.println("Replica immediately: " + read("deal-1", true));
        System.out.println("Primary immediately: " + read("deal-1", false));

        syncReplica();
        System.out.println("Replica after sync: " + read("deal-1", true));
    }
}