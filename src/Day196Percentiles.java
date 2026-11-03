import java.util.Arrays;

/**
 * Percentiles by the nearest-rank method: rank = ceil(p / 100 * n),
 * and the answer is the value at that rank (1-based, so index
 * rank - 1). The caller's array is never modified (a sorted COPY is
 * used), and empty input or a p outside 0-100 is rejected.
 */
public class Day196Percentiles {

    private static double percentile(double[] values, double p) {
        if (values.length == 0) {
            throw new IllegalArgumentException("values must not be empty");
        }
        if (p < 0 || p > 100) {
            throw new IllegalArgumentException("p must be between 0 and 100");
        }
        double[] ordered = values.clone();
        Arrays.sort(ordered);
        int rank = Math.max(1, (int) Math.ceil(p * ordered.length / 100.0));
        return ordered[rank - 1];
    }

    public static void main(String[] args) {
        double[] latenciesMs = {120, 95, 180, 110, 3000, 105, 130, 99, 101, 115};
        System.out.println(percentile(latenciesMs, 50));
        System.out.println(Arrays.toString(latenciesMs));
        System.out.println(percentile(latenciesMs, 95));
        System.out.println(percentile(latenciesMs, 100));
        System.out.println(Arrays.stream(latenciesMs).average().getAsDouble());
    }
}