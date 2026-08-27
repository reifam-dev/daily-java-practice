import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

public class Day138ErrorQuiz {

    private TreeMap<Long, String> ring = new TreeMap<>();

    private long hash(String key) {
        return key.hashCode();
    }

    public void addNode(String nodeName) {
        long h = hash(nodeName);
        ring.put(h, nodeName)
    }

    public String getNode(String key) {
        long h = hash(key);
        Long nodeHash = ring.ceilingKey(h);
        return ring.get(nodeHash);
    }

    public static void main(String[] args) {
        Day138ErrorQuiz store = new Day138ErrorQuiz();
        store.addNode("shard-a");
        store.addNode("shard-b");
        store.addNode("shard-c");

        String[] dealIds = {"deal-1", "deal-2", "deal-3", "deal-4"};
        for (String dealId : dealIds) {
            System.out.println(dealId + " -> " + store.getNode(dealId));
        }
    }
}