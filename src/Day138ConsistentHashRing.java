import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Places shards on a hash ring using a stable, cryptographic hash, so
 * a key always routes to the next shard clockwise, wrapping around
 * to the first shard past the highest point on the ring.
 */
public class Day138ConsistentHashRing {

    private final TreeMap<BigInteger, String> ring = new TreeMap<>();

    private BigInteger hash(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(key.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }

    public void addNode(String nodeName) {
        BigInteger h = hash(nodeName);
        this.ring.put(h, nodeName);
    }

    public void removeNode(String nodeName) {
        BigInteger h = hash(nodeName);
        this.ring.remove(h);
    }

    public String getNode(String key) {
        if (this.ring.isEmpty()) {
            throw new IllegalStateException("No shards registered");
        }

        BigInteger h = hash(key);
        Map.Entry<BigInteger, String> entry = this.ring.ceilingEntry(h);
        if (entry == null) {
            entry = this.ring.firstEntry();
        }
        return entry.getValue();
    }

    public static void main(String[] args) {
        Day138ConsistentHashRing ring = new Day138ConsistentHashRing();
        ring.addNode("shard-a");
        ring.addNode("shard-b");
        ring.addNode("shard-c");

        String[] dealIds = {"deal-1", "deal-2", "deal-3", "deal-4"};
        for (String dealId : dealIds) {
            System.out.println(dealId + " -> " + ring.getNode(dealId));
        }
    }
}