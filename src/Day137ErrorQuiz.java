import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day137ErrorQuiz {

    private static final int NUM_SHARDS = 4;
    private List<Map<String, Object>> shards;

    public Day137ErrorQuiz(int numShards) {
        shards = new ArrayList<>();
        for (int i = 0; i < numShards; i++) {
            shards.add(new HashMap<>());
        }
    }

    private int shardForKey(String key) {
        return key.hashCode() % shards.size();
    }

    public void put(String key, Object value) {
        int shardIndex = shardForKey(key);
        shards.get(shardIndex).put(key, value);
    }

    public Object get(String key) {
        int shardIndex = shardForKey(key);
        return shards.get(shardIndex).get(key)
    }

    public static void main(String[] args) {
        Day137ErrorQuiz store = new Day137ErrorQuiz(NUM_SHARDS);

        for (int i = 0; i < 20; i++) {
            String dealId = "deal-" + i;
            store.put(dealId, "Deal " + i);
        }

        System.out.println("deal-7: " + store.get("deal-7"));
        System.out.println("deal-15: " + store.get("deal-15"));
    }
}