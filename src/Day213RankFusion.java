import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Each ranked list gives a document 1 / (k + rank) (ranks start at 1), and
 * a document's fused score is the SUM across every list it appears in, so
 * agreement between retrievers is rewarded. Results are ordered best first
 * and ties break alphabetically so the output is deterministic.
 */
public class Day213RankFusion {

    private static List<Map.Entry<String, Double>> reciprocalRankFusion(
            List<List<String>> rankings, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        Map<String, Double> scores = new HashMap<>();
        for (List<String> ranking : rankings) {
            for (int i = 0; i < ranking.size(); i++) {
                int rank = i + 1;
                scores.merge(ranking.get(i), 1.0 / (k + rank), Double::sum);
            }
        }
        List<Map.Entry<String, Double>> entries = new ArrayList<>(scores.entrySet());
        entries.sort((a, b) -> {
            int byScore = Double.compare(b.getValue(), a.getValue());
            return byScore != 0 ? byScore : a.getKey().compareTo(b.getKey());
        });
        return entries;
    }

    public static void main(String[] args) {
        List<String> keyword = List.of("d3", "d1", "d7", "d2");
        List<String> vector = List.of("d1", "d9", "d3", "d4");
        for (Map.Entry<String, Double> entry : reciprocalRankFusion(List.of(keyword, vector), 60)) {
            System.out.printf("%s %.5f%n", entry.getKey(), entry.getValue());
        }
    }
}