import java.util.List;
import java.util.Set;

/**
 * precision@k = relevant documents in the top k / k. recall@k = relevant
 * documents in the top k / ALL relevant documents. Reciprocal rank = 1 /
 * the 1-based rank of the first relevant document, or 0 if none was
 * retrieved; MRR averages it over ALL queries, misses counting as 0.
 */
public class Day204RetrievalMetrics {

    private static int hitsInTopK(List<String> retrieved, Set<String> relevant, int k) {
        int hits = 0;
        for (int i = 0; i < Math.min(k, retrieved.size()); i++) {
            if (relevant.contains(retrieved.get(i))) {
                hits++;
            }
        }
        return hits;
    }

    private static double precisionAtK(List<String> retrieved, Set<String> relevant, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        return (double) hitsInTopK(retrieved, relevant, k) / k;
    }

    private static double recallAtK(List<String> retrieved, Set<String> relevant, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        if (relevant.isEmpty()) {
            throw new IllegalArgumentException("relevant must not be empty");
        }
        return (double) hitsInTopK(retrieved, relevant, k) / relevant.size();
    }

    private static double reciprocalRank(List<String> retrieved, Set<String> relevant) {
        for (int i = 0; i < retrieved.size(); i++) {
            if (relevant.contains(retrieved.get(i))) {
                return 1.0 / (i + 1);
            }
        }
        return 0.0;
    }

    private static double meanReciprocalRank(List<List<String>> retrievedRuns,
                                             List<Set<String>> relevantRuns) {
        if (retrievedRuns.isEmpty()) {
            throw new IllegalArgumentException("runs must not be empty");
        }
        double sum = 0.0;
        for (int q = 0; q < retrievedRuns.size(); q++) {
            sum += reciprocalRank(retrievedRuns.get(q), relevantRuns.get(q));
        }
        return sum / retrievedRuns.size();
    }

    public static void main(String[] args) {
        List<List<String>> retrieved = List.of(
                List.of("d3", "d1", "d7", "d2"), List.of("d5", "d6", "d4"), List.of("d8", "d9"));
        List<Set<String>> relevant = List.of(Set.of("d1", "d2"), Set.of("d4"), Set.of("d1"));
        System.out.printf("%.4f%n", precisionAtK(retrieved.get(0), relevant.get(0), 3));
        System.out.printf("%.4f%n", recallAtK(retrieved.get(0), relevant.get(0), 3));
        System.out.printf("%.4f%n", reciprocalRank(retrieved.get(1), relevant.get(1)));
        System.out.printf("%.4f%n", meanReciprocalRank(retrieved, relevant));
    }
}