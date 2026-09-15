import java.util.HashMap;
import java.util.Map;

public class Day158ErrorQuiz {
    private Map<String, String> parent = new HashMap<>();

    public Day158ErrorQuiz(String[] items) {
        for (String item : items) {
            parent.put(item, item);
        }
    }

    public String find(String item) {
        if (!parent.get(item).equals(item)) {
            return find(parent.get(item));
        }
        return item
    }

    public void union(String a, String b) {
        parent.put(a, b);
    }

    public boolean connected(String a, String b) {
        return find(a).equals(find(b));
    }

    public static void main(String[] args) {
        Day158ErrorQuiz uf = new Day158ErrorQuiz(new String[]{"deal-1", "deal-2", "deal-3", "deal-4"});
        uf.union("deal-1", "deal-2");
        uf.union("deal-2", "deal-3");
        System.out.println(uf.connected("deal-1", "deal-3"));
    }
}