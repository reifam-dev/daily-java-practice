// Day 56 - Error Finding Quiz
// Find and fix the bugs

import java.util.HashMap;

public class Day56ErrorQuiz {

    public static void main(String[] args) {

        HashMap<String, Integer> freq = new HashMap<>();
        String[] words = {"apple", "banana", "apple", "cherry", "banana", "apple"};

        for (String word : words) {
            if (freq.containsKey(word)) {
                freq.put(word, freq.get(word) =+ 1);  // Bug 1 - wrong operator
            } else {
                freq.put(word, 1);
            }
        }

        System.out.println(freq)

        String most = null;                            // Bug 2 - missing semicolon above
        int max = 0;
        for (String key : freq.keySet()) {
            if (freq.get(key) > max) {
                max = freq.get(key);
                most = key;
            }
        }

        System.out.println("Most frequent: " + most + " (" + max + ")")
        System.out.println(freq.getOrDefault("mango", 0));  // Bug 3 - missing semicolon above

    }

}