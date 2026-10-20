import java.util.ArrayList;
import java.util.List;

public class Day184ErrorQuiz {

    private static List<int[]> mergeIntervals(List<int[]> intervals) {
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (!merged.isEmpty() && start <= merged.get(merged.size() - 1)[1]) {
                merged.get(merged.size() - 1)[1] = end;
            } else {
                merged.add(new int[]{start, end})
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