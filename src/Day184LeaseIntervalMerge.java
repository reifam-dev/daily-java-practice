import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Leases are {startMonth, endMonth} pairs. Sort by start first (on a
 * copy), then sweep once: a lease that starts at or before the current
 * block's end (touching counts) extends the block to the LATER of the
 * two ends. The merged total is true occupied time, overlaps counted once.
 */
public class Day184LeaseIntervalMerge {

    private static List<int[]> mergeIntervals(List<int[]> intervals) {
        List<int[]> sorted = new ArrayList<>(intervals);
        sorted.sort(Comparator.comparingInt((int[] interval) -> interval[0]));

        List<int[]> merged = new ArrayList<>();
        for (int[] interval : sorted) {
            int start = interval[0];
            int end = interval[1];
            if (!merged.isEmpty() && start <= merged.get(merged.size() - 1)[1]) {
                int[] last = merged.get(merged.size() - 1);
                last[1] = Math.max(last[1], end);
            } else {
                merged.add(new int[]{start, end});
            }
        }
        return merged;
    }

    public static void main(String[] args) {
        List<int[]> leases = List.of(
                new int[]{24, 36}, new int[]{0, 12}, new int[]{6, 18},
                new int[]{36, 48}, new int[]{30, 32});
        int covered = 0;
        for (int[] block : mergeIntervals(leases)) {
            System.out.println(block[0] + "-" + block[1]);
            covered += block[1] - block[0];
        }
        System.out.println(covered);
    }
}