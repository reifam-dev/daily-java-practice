/**
 * What can a developer pay for the site? Start from the completed value
 * (GDV) and subtract everything else. Finance accrues on a build that is
 * drawn down gradually, so on AVERAGE only half the cost is outstanding;
 * profit is a share of GDV; and purchaser's costs sit ON TOP of the land
 * price, so the price is the residual DIVIDED by (1 + costs).
 */
public class Day198ResidualLandValue {

    private static double residualLandValue(double gdv, double buildCost, double feesPct,
                                            double contingencyPct, double financeRate,
                                            int buildMonths, double profitOnGdvPct,
                                            double landCostsPct) {
        if (gdv <= 0 || buildCost < 0 || buildMonths <= 0) {
            throw new IllegalArgumentException(
                    "gdv and buildMonths must be positive, buildCost non-negative");
        }
        double costs = buildCost * (1 + feesPct + contingencyPct);
        double finance = costs * financeRate * (buildMonths / 12.0) / 2;
        double profit = gdv * profitOnGdvPct;
        double residual = gdv - costs - finance - profit;
        return Math.max(residual, 0.0) / (1 + landCostsPct);
    }

    public static void main(String[] args) {
        System.out.printf("%.0f%n", residualLandValue(
                30000000.0, 15000000.0, 0.12, 0.05, 0.07, 18, 0.20, 0.068));
        System.out.printf("%.0f%n", residualLandValue(
                20000000.0, 15000000.0, 0.12, 0.05, 0.07, 18, 0.20, 0.068));
    }
}