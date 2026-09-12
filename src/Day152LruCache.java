#import java.util.LinkedHashMap;
import java.util.Map;

public class Day152LruCache extends LinkedHashMap<String, String> {
    private final int capacity;

    public Day152LruCache(int capacity) {
        super(16, 0.75f, true);
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
        return size() > this.capacity;
    }

    public static void main(String[] args) {
        Day152LruCache cache = new Day152LruCache(2);
        cache.put("deal-1", "Riverside JV");
        cache.put("deal-2", "Westgate Retail");
        cache.get("deal-1");
        cache.put("deal-3", "Logistics Portfolio");

        System.out.println(cache.get("deal-1"));
        System.out.println(cache.get("deal-2"));
        System.out.println(cache.get("deal-3"));
    }
}