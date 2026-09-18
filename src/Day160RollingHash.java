import java.util.ArrayList;
import java.util.List;

/**
 * Computes a substring hash in O(1) as the window slides, avoiding
 * O(m) recomputation per position - a Java analogue of the Python
 * rolling hash pattern-matching approach.
 */
public class Day160RollingHash {
    private static final long BASE = 256;
    private static final long MOD = 1_000_000_007L;

    private static long computeHash(String s) {
        long h = 0;
        for (char c : s.toCharArray()) {
            h = (h * BASE + c) % MOD;
        }
        return h;
    }

    private static long highOrder(int windowSize) {
        long result = 1;
        for (int i = 0; i < windowSize - 1; i++) {
            result = (result * BASE) % MOD;
        }
        return result;
    }

    private static long rollHash(long oldHash, char oldChar, char newChar, long highOrderTerm) {
        long h = ((oldHash - oldChar * highOrderTerm) % MOD + MOD) % MOD;
        h = (h * BASE + newChar) % MOD;
        return h;
    }

    private static List<Integer> findPattern(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        if (m > n) {
            return new ArrayList<>();
        }

        long patternHash = computeHash(pattern);
        long highOrderTerm = highOrder(m);
        List<Integer> matches = new ArrayList<>();

        long windowHash = computeHash(text.substring(0, m));
        for (int i = 0; i <= n - m; i++) {
            if (windowHash == patternHash && text.substring(i, i + m).equals(pattern)) {
                matches.add(i);
            }
            if (i < n - m) {
                windowHash = rollHash(windowHash, text.charAt(i), text.charAt(i + m), highOrderTerm);
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        System.out.println(findPattern("abracadabra", "abra"));
    }
}