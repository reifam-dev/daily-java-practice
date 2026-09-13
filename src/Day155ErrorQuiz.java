import java.util.HashMap;
import java.util.Map;

public class Day155ErrorQuiz {
    private static Map<Integer, Long> cache = new HashMap<>();

    private static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        long result = fibonacci(n - 1) + fibonacci(n - 2)
        return result;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(fibonacci(40));
        System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
    }
}