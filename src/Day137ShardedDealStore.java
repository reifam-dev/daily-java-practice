import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Distributes records across a fixed number of shards using a stable
 * hash of the partition key, so the same key always routes to the
 * same shard.
 */
public class Day137ShardedDealStore {

    private final List<Map<String, Object>> shards;

    public Day137ShardedDealStore(int numShards) {
        if (numShards < 1) {
            throw new IllegalArgumentException("numShards must be at least 1");
        }
        this.shards = new ArrayList<>();
        for (int i = 0; i < numShards; i++) {
            this.shards.add(new HashMap<>());
        }
    }

    private int shardForKey(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(key.getBytes(StandardCharsets.UTF_8));

            long unsignedValue = 0;
            for (int i = 0; i < 4; i++) {
                unsignedValue = (unsignedValue << 8) | (hash[i] & 0xFF);
            }
            return (int) (Math.abs(unsignedValue) % this.shards.size());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }

    public void put(String key, Object value) {
        int shardIndex = shardForKey(key);
        this.shards.get(shardIndex).put(key, value);
    }

    public Object get(String key) {
        int shardIndex = shardForKey(key);
        return this.shards.get(shardIndex).get(key);
    }

    public List<Integer> shardSizes() {
        List<Integer> sizes = new ArrayList<>();
        for (Map<String, Object> shard : this.shards) {
            sizes.add(shard.size());
        }
        return sizes;
    }

    public static void main(String[] args) {
        Day137ShardedDealStore store = new Day137ShardedDealStore(4);

        for (int i = 0; i < 20; i++) {
            String dealId = "deal-" + i;
            store.put(dealId, "Deal " + i);
        }

        System.out.println("Shard sizes: " + store.shardSizes());
        System.out.println("deal-7: " + store.get("deal-7"));
    }
}