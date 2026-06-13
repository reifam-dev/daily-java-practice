import java.util.LinkedHashMap;

public class Day64ContainerProtocol {

    public static void main(String[] args) {

        System.out.println("=== SimpleCache ===\n");
        SimpleCache cache = new SimpleCache(3);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        System.out.println("  " + cache);
        System.out.println("  'a' in cache: " + cache.contains("a"));
        System.out.println("  size: " + cache.size());

        cache.put("d", 4);
        System.out.println("  After adding 'd': " + cache);
        System.out.println("  'a' in cache: " + cache.contains("a"));

        System.out.println("  get('b'): " + cache.get("b"));
        System.out.println("  get('z'): " + cache.get("z"));

        System.out.println("\n=== Matrix ===\n");
        Matrix m = new Matrix(3, 3);
        m.set(0, 0, 5);
        m.set(1, 1, 10);
        m.set(2, 2, 15);
        System.out.println("  Matrix:\n" + m);
        System.out.println("  m[1][1] = " + m.get(1, 1));
        System.out.println("  m[5][5] = " + m.get(5, 5));

    }

}

class SimpleCache {

    private LinkedHashMap<String, Integer> store;
    private int maxSize;

    public SimpleCache(int maxSize) {
        this.maxSize = maxSize;
        this.store = new LinkedHashMap<>();
    }

    public void put(String key, int value) {
        if (store.containsKey(key)) {
            store.remove(key);
        } else if (store.size() >= maxSize) {
            String oldest = store.keySet().iterator().next();
            store.remove(oldest);
        }
        store.put(key, value);
    }

    public Integer get(String key) {
        return store.getOrDefault(key, null);
    }

    public boolean contains(String key) {
        return store.containsKey(key);
    }

    public int size() {
        return store.size();
    }

    @Override
    public String toString() {
        return "SimpleCache" + store;
    }

}

class Matrix {

    private int[][] data;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    public void set(int row, int col, int value) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.printf("  Index (%d,%d) out of bounds.%n", row, col);
            return;
        }
        data[row][col] = value;
    }

    public Integer get(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.printf("  Index (%d,%d) out of bounds.%n", row, col);
            return null;
        }
        return data[row][col];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : data) {
            sb.append("  [");
            for (int i = 0; i < row.length; i++) {
                sb.append(row[i]);
                if (i < row.length - 1) sb.append(", ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

}