import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEnd = false;
}

/**
 * Prefix-tree autocomplete over deal names, correctly collecting
 * every completed word under a prefix via recursive traversal.
 */
public class Day157DealNameTrie {
    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = this.root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEnd = true;
    }

    private void collect(TrieNode node, String prefix, List<String> results) {
        if (node.isEnd) {
            results.add(prefix);
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            collect(entry.getValue(), prefix + entry.getKey(), results);
        }
    }

    public List<String> startsWith(String prefix) {
        TrieNode node = this.root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return new ArrayList<>();
            }
            node = node.children.get(c);
        }
        List<String> results = new ArrayList<>();
        collect(node, prefix, results);
        return results;
    }

    public static void main(String[] args) {
        Day157DealNameTrie trie = new Day157DealNameTrie();
        trie.insert("Riverside JV");
        trie.insert("Riverside Park");
        trie.insert("Westgate Retail");
        System.out.println(trie.startsWith("River"));
    }
}