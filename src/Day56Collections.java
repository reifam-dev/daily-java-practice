import java.util.ArrayDeque;
import java.util.HashMap;

public class Day56Collections {

    public static void main(String[] args) {

        System.out.println("=== HashMap word frequency ===\n");
        String[] words = {"apple", "banana", "apple", "cherry", "banana", "apple"};
        HashMap<String, Integer> freq = new HashMap<>();

        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        System.out.println("  Frequencies : " + freq);

        String mostFrequent = null;
        int max = 0;
        for (String key : freq.keySet()) {
            if (freq.get(key) > max) {
                max = freq.get(key);
                mostFrequent = key;
            }
        }
        System.out.println("  Most frequent: " + mostFrequent + " (" + max + ")\n");

        System.out.println("=== ArrayDeque — sliding window ===\n");
        ArrayDeque<Integer> window = new ArrayDeque<>();
        int maxSize = 3;
        for (int i = 1; i <= 6; i++) {
            if (window.size() == maxSize) {
                window.pollFirst();
            }
            window.addLast(i);
            System.out.println("  Added " + i + " → window: " + window);
        }

        System.out.println("\n=== ArrayDeque — browser history ===\n");
        ArrayDeque<String> history = new ArrayDeque<>();
        String[] pages = {"home", "about", "contact", "shop"};
        for (String page : pages) {
            history.addFirst(page);
        }
        System.out.println("  History (newest first): " + history);
        System.out.println("  Current page          : " + history.peekFirst());

    }

}