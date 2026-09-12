import java.util.LinkedHashMap;
import java.util.Map;

public class Day152ErrorQuiz {
    private int capacity;
    private Map<String, String> cache = new LinkedHashMap<>();

    public Day152ErrorQuiz(int capacity) {
        this.capacity = capacity;
    }

    public String get(String key) {
        return cache.get(key);
    }

    public void put(String key, String value) {
        cache.put(key, value);
        if (cache.size() > capacity) {
            String oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey)
        }
    }

    public static void main(String[] args) {
        Day152ErrorQuiz cache = new Day152ErrorQuiz(2);
        cache.put("deal-1", "Riverside JV");
        cache.put("deal-2", "Westgate Retail");
        cache.get("deal-1");
        cache.put("deal-3", "Logistics Portfolio");

        System.out.println(cache.get("deal-1"));
        System.out.println(cache.get("deal-2"));
    }
}