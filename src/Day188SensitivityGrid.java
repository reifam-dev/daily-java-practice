import java.util.Arrays;

/**
 * Two-way sensitivity grid: exit value by NOI growth (rows) and exit
 * cap rate (columns). NOI grows with COMPOUND interest, row and column
 * indices are kept in step with their arrays, and cap rates are
 * validated before any division.
 */
public class Day188SensitivityGrid {

    private static long[][] sensitivityGrid(
            double noi, double[] growthRates, double[] capRates, int years) {
        for (double capRate : capRates) {
            if (capRate <= 0) {
                throw new IllegalArgumentException("every cap rate must be positive");
            }
        }
        long[][] grid = new long[growthRates.length][capRates.length];
        for (int i = 0; i < growthRates.length; i++) {
            double exitNoi = noi * Math.pow(1 + growthRates[i], years);
            for (int j = 0; j < capRates.length; j++) {
                grid[i][j] = Math.round(exitNoi / capRates[j]);
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        double[] growthRates = {0.0, 0.02, 0.04};
        double[] capRates = {0.05, 0.055, 0.06, 0.065};
        for (long[] row : sensitivityGrid(1000000.0, growthRates, capRates, 5)) {
            System.out.println(Arrays.toString(row));
        }
        try {
            sensitivityGrid(1000000.0, new double[]{0.02}, new double[]{0.0}, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}