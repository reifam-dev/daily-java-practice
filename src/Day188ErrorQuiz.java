public class Day188ErrorQuiz {

    private static long[][] sensitivityGrid(
            double noi, double[] growthRates, double[] capRates, int years) {
        long[][] grid = new long[growthRates.length][capRates.length];
        for (int i = 0; i < growthRates.length; i++) {
            for (int j = 0; j < capRates.length; j++) {
                double exitNoi = noi * (1 + growthRates[i] * years)
                grid[j][i] = Math.round(exitNoi / capRates[j]);
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        double[] growthRates = {0.0, 0.02, 0.04};
        double[] capRates = {0.05, 0.055, 0.06, 0.065};
        for (long[] row : sensitivityGrid(1000000.0, growthRates, capRates, 5)) {
            System.out.println(java.util.Arrays.toString(row));
        }
    }
}