import java.util.ArrayList;
import java.util.List;

public class Day160ErrorQuiz {
    private static final int BASE = 256;

    private static long computeHash(String s) {
        long h = 0;
        for (char c : s.toCharArray()) {
            h = h * BASE + c;
        }
        return h;
    }

    private static List<Integer> findPattern(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        long patternHash = computeHash(pattern);
        List<Integer> matches = new ArrayList<>();

        for (int i = 0; i < n - m; i++) {
            long windowHash = computeHash(text.substring(i, i + m));
            if (windowHash == patternHash) {
                matches.add(i)
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        System.out.println(findPattern("abracadabra", "abra"));
    }
}