public class Day198ErrorQuiz {

    private static double residualLandValue(double gdv, double buildCost, double feesPct,
                                            double contingencyPct, double financeRate,
                                            int buildMonths, double profitOnGdvPct,
                                            double landCostsPct) {
        double costs = buildCost * (1 + feesPct + contingencyPct);
        double finance = costs * financeRate * (buildMonths / 12.0);
        double profit = costs * profitOnGdvPct
        double residual = gdv - costs - finance - profit;
        return residual / (1 + landCostsPct);
    }

    public static void main(String[] args) {
        System.out.printf("%.0f%n", residualLandValue(
                30000000.0, 15000000.0, 0.12, 0.05, 0.07, 18, 0.20, 0.068));
    }
}