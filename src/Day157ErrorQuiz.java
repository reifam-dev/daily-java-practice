import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEnd = false;
}

public class Day157ErrorQuiz {
    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEnd = true
    }

    public List<String> startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return new ArrayList<>();
            }
            node = node.children.get(c);
        }
        List<String> results = new ArrayList<>();
        results.add(prefix);
        return results;
    }

    public static void main(String[] args) {
        Day157ErrorQuiz trie = new Day157ErrorQuiz();
        trie.insert("Riverside JV");
        trie.insert("Riverside Park");
        System.out.println(trie.startsWith("River"));
    }
}