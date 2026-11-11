import java.util.List;
import java.util.Set;

public class Day204ErrorQuiz {

    private static double precisionAtK(List<String> retrieved, Set<String> relevant, int k) {
        int hits = 0;
        for (int i = 0; i < Math.min(k, retrieved.size()); i++) {
            if (relevant.contains(retrieved.get(i))) {
                hits++;
            }
        }
        return (double) hits / relevant.size();
    }

    private static double reciprocalRank(List<String> retrieved, Set<String> relevant) {
        for (int i = 0; i < retrieved.size(); i++) {
            if (relevant.contains(retrieved.get(i))) {
                return 1.0 / (i + 1);
            }
        }
        return 0.0;
    }

    private static double meanReciprocalRank(List<List<String>> retrievedRuns, List<Set<String>> relevantRuns) {
        double sum = 0.0;
        int found = 0;
        for (int q = 0; q < retrievedRuns.size(); q++) {
            double score = reciprocalRank(retrievedRuns.get(q), relevantRuns.get(q));
            if (score > 0) {
                sum += score;
                found++;
            }
        }
        return sum / found
    }

    public static void main(String[] args) {
        List<List<String>> retrieved = List.of(
                List.of("d3", "d1", "d7", "d2"), List.of("d5", "d6", "d4"), List.of("d8", "d9"));
        List<Set<String>> relevant = List.of(Set.of("d1", "d2"), Set.of("d4"), Set.of("d1"));
        System.out.printf("%.4f%n", precisionAtK(retrieved.get(0), relevant.get(0), 3));
        System.out.printf("%.4f%n", reciprocalRank(retrieved.get(1), relevant.get(1)));
        System.out.printf("%.4f%n", meanReciprocalRank(retrieved, relevant));
    }
}