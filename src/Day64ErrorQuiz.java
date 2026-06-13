// Day 64 - Error Finding Quiz
// Find and fix the bugs

import java.util.HashMap;

public class Day64ErrorQuiz {

    private HashMap<String, Integer> store = new HashMap<>();
    private int maxSize;
    private String label;

    public Day64ErrorQuiz(String label, int maxSize) {
        label = label;            // Bug 1 - missing this
        this.maxSize = maxSize;
    }

    public void put(String key, int value) {
        if (store.size() >= maxSize) {
            System.out.println("Cache full.");
            return;
        }
        store.put(key, value =+ 0);  // Bug 2 - wrong operator, should just be value
    }

    public int get(String key) {
        return store.get(key);    // Bug 3 - NullPointerException if key not found
    }

    public static void main(String[] args) {
        Day64ErrorQuiz cache = new Day64ErrorQuiz("MyCache", 3);
        cache.put("a", 1);
        cache.put("b", 2);
        System.out.println(cache.get("a"));
        System.out.println(cache.get("z"));
    }

}// Day 64 - Error Finding Quiz
// Find and fix the bugs

import java.util.HashMap;

public class Day64ErrorQuiz {

    private HashMap<String, Integer> store = new HashMap<>();
    private int maxSize;
    private String label;

    public Day64ErrorQuiz(String label, int maxSize) {
        label = label;            // Bug 1 - missing this
        this.maxSize = maxSize;
    }

    public void put(String key, int value) {
        if (store.size() >= maxSize) {
            System.out.println("Cache full.");
            return;
        }
        store.put(key, value =+ 0);  // Bug 2 - wrong operator, should just be value
    }

    public int get(String key) {
        return store.get(key);    // Bug 3 - NullPointerException if key not found
    }

    public static void main(String[] args) {
        Day64ErrorQuiz cache = new Day64ErrorQuiz("MyCache", 3);
        cache.put("a", 1);
        cache.put("b", 2);
        System.out.println(cache.get("a"));
        System.out.println(cache.get("z"));
    }

}