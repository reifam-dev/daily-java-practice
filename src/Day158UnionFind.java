import java.util.HashMap;
import java.util.Map;

/**
 * Groups items into connected clusters using path compression so
 * repeated find() calls stay fast as the structure grows.
 */
public class Day158UnionFind {
    private final Map<String, String> parent = new HashMap<>();

    public Day158UnionFind(String[] items) {
        for (String item : items) {
            this.parent.put(item, item);
        }
    }

    public String find(String item) {
        if (!this.parent.get(item).equals(item)) {
            this.parent.put(item, find(this.parent.get(item)));
        }
        return this.parent.get(item);
    }

    public void union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);
        if (!rootA.equals(rootB)) {
            this.parent.put(rootA, rootB);
        }
    }

    public boolean connected(String a, String b) {
        return find(a).equals(find(b));
    }

    public static void main(String[] args) {
        Day158UnionFind uf = new Day158UnionFind(new String[]{"deal-1", "deal-2", "deal-3", "deal-4"});
        uf.union("deal-1", "deal-2");
        uf.union("deal-2", "deal-3");
        System.out.println(uf.connected("deal-1", "deal-3"));
        System.out.println(uf.connected("deal-1", "deal-4"));
    }
}