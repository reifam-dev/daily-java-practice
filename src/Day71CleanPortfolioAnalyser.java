import java.util.ArrayList;

/**
 * Day 71 – Advanced NumPy equivalents in Java: array operations and statistics.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day71CleanPortfolioAnalyser {

    private String portfolioName;
    private double[] returns;
    private double[] benchmark;
    private int size;

    public Day71CleanPortfolioAnalyser(String portfolioName, double[] returns) {
        this.portfolioName = portfolioName;
        this.returns = returns;
        this.size = returns.length;
        this.benchmark = new double[this.size];
    }

    public void setBenchmark(double[] benchmark) {
        this.benchmark = benchmark;
    }

    public String getPortfolioName() {
        return this.portfolioName;
    }

    public double[] getReturns() {
        return this.returns;
    }

    public double meanReturn() {
        double sum = 0.0;
        for (double r : this.returns) {
            sum += r;
        }
        return sum / this.size;
    }

    public double volatility() {
        double mean = meanReturn();
        double sumSq = 0.0;
        for (double r : this.returns) {
            sumSq += Math.pow(r - mean, 2);
        }
        return Math.sqrt(sumSq / this.size);
    }

    public double sharpeRatio(double riskFree) {
        double mean = meanReturn();
        double vol = volatility();
        return (mean - riskFree) / vol;
    }

    public double[] activeReturns() {
        double[] active = new double[this.size];
        for (int i = 0; i < this.size; i++) {
            active[i] = this.returns[i] - this.benchmark[i];
        }
        return active;
    }

    public ArrayList<Double> aboveThreshold(double threshold) {
        ArrayList<Double> result = new ArrayList<>();
        for (double r : this.returns) {
            if (r > threshold) {
                result.add(r);
            }
        }
        return result;
    }

    public double cumulativeReturn() {
        double product = 1.0;
        for (double r : this.returns) {
            product *= (1 + r);
        }
        return product - 1;
    }

    @Override
    public String toString() {
        return String.format(
                "Portfolio: %s | n=%d | Mean=%.4f | Vol=%.4f",
                this.portfolioName, this.size, meanReturn(), volatility()
        );
    }

    public static void main(String[] args) {
        double[] returns = {0.05, 0.12, -0.03, 0.08, 0.15, -0.02, 0.09};
        double[] benchmark = {0.04, 0.08, 0.01, 0.06, 0.10, 0.02, 0.07};

        Day71CleanPortfolioAnalyser pa =
                new Day71CleanPortfolioAnalyser("Growth Fund", returns);
        pa.setBenchmark(benchmark);

        System.out.println(pa);
        System.out.printf("Sharpe Ratio  : %.4f%n", pa.sharpeRatio(0.02));
        System.out.println("Above 5%      : " + pa.aboveThreshold(0.05));
        System.out.printf("Cumulative    : %.4f%n", pa.cumulativeReturn());
    }
}