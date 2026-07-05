import java.util.ArrayList;

/**
 * Day 83 – Pandas time series concepts in Java: rolling averages, cumulative returns.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day83TimeSeriesAnalyser {

    private String seriesName;
    private ArrayList<Double> returns;
    private int periodsPerYear;

    public Day83TimeSeriesAnalyser(String seriesName, int periodsPerYear) {
        this.seriesName = seriesName;
        this.returns = new ArrayList<>();
        this.periodsPerYear = periodsPerYear;
    }

    public String getSeriesName() { return this.seriesName; }
    public int getSize() { return this.returns.size(); }

    public void addReturn(double r) {
        this.returns.add(r);
    }

    public double mean() {
        if (this.returns.isEmpty()) return 0.0;
        double total = 0.0;
        for (double r : this.returns) total += r;
        return total / this.returns.size();
    }

    public double cumulativeReturn() {
        double product = 1.0;
        for (double r : this.returns) product *= (1 + r);
        return product - 1;
    }

    public double annualisedReturn() {
        double cumulative = cumulativeReturn();
        int n = this.returns.size();
        if (n == 0) return 0.0;
        return Math.pow(1 + cumulative, (double) this.periodsPerYear / n) - 1;
    }

    public ArrayList<Double> rollingAverage(int window) {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < this.returns.size(); i++) {
            if (i < window - 1) {
                result.add(Double.NaN);
                continue;
            }
            double sum = 0.0;
            for (int j = i - window + 1; j <= i; j++) sum += this.returns.get(j);
            result.add(sum / window);
        }
        return result;
    }

    public ArrayList<Double> cumulativeSeries() {
        ArrayList<Double> result = new ArrayList<>();
        double product = 1.0;
        for (double r : this.returns) {
            product *= (1 + r);
            result.add(product - 1);
        }
        return result;
    }

    public double maxDrawdown() {
        double peak = Double.NEGATIVE_INFINITY;
        double maxDD = 0.0;
        double cumulative = 1.0;
        for (double r : this.returns) {
            cumulative *= (1 + r);
            if (cumulative > peak) peak = cumulative;
            double dd = (cumulative - peak) / peak;
            if (dd < maxDD) maxDD = dd;
        }
        return maxDD;
    }

    public void printStats() {
        System.out.printf("Series       : %s%n", this.seriesName);
        System.out.printf("Periods      : %d%n", this.returns.size());
        System.out.printf("Mean return  : %.4f%n", mean());
        System.out.printf("Cumulative   : %.4f%n", cumulativeReturn());
        System.out.printf("Annualised   : %.4f%n", annualisedReturn());
        System.out.printf("Max drawdown : %.4f%n", maxDrawdown());
    }

    @Override
    public String toString() {
        return String.format(
                "TimeSeriesAnalyser | series=%s | n=%d | cumReturn=%.4f",
                this.seriesName, this.returns.size(), cumulativeReturn()
        );
    }

    public static void main(String[] args) {
        Day83TimeSeriesAnalyser ts =
                new Day83TimeSeriesAnalyser("Office Returns", 12);
        double[] data = {0.04, 0.03, 0.05, 0.02, 0.06, 0.03, 0.04, 0.05, 0.02, 0.03, 0.06, 0.04};
        for (double r : data) ts.addReturn(r);

        ts.printStats();
        System.out.println("\nRolling 3-month average: " + ts.rollingAverage(3));
        System.out.println("Cumulative series: " + ts.cumulativeSeries());
        System.out.println("\n" + ts);
    }
}