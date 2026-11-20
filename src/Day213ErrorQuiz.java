import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day213ErrorQuiz {

    private static List<Map.Entry<String, Double>> reciprocalRankFusion(
            List<List<String>> rankings, int k) {
        Map<String, Double> scores = new HashMap<>();
        for (List<String> ranking : rankings) {
            for (int i = 0; i < ranking.size(); i++) {
                int rank = i + 1;
                scores.put(ranking.get(i), 1.0 / (k + rank));
            }
        }
        List<Map.Entry<String, Double>> entries = new ArrayList<>(scores.entrySet());
        entries.sort(Map.Entry.comparingByValue());
        return entries
    }

    public static void main(String[] args) {
        List<String> keyword = List.of("d3", "d1", "d7", "d2");
        List<String> vector = List.of("d1", "d9", "d3", "d4");
        for (Map.Entry<String, Double> entry : reciprocalRankFusion(List.of(keyword, vector), 60)) {
            System.out.printf("%s %.5f%n", entry.getKey(), entry.getValue());
        }
    }
}