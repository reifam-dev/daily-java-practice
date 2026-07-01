import java.util.ArrayList;

/**
 * Day 82 – NumPy linear algebra concepts in Java: covariance, variance, Sharpe ratio.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day82PortfolioOptimiser {

    private String portfolioName;
    private ArrayList<Double> returns;
    private double riskFreeRate;

    public Day82PortfolioOptimiser(String portfolioName, double riskFreeRate) {
        this.portfolioName = portfolioName;
        this.returns = new ArrayList<>();
        this.riskFreeRate = riskFreeRate;
    }

    public String getPortfolioName() { return this.portfolioName; }
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

    public double variance() {
        if (this.returns.isEmpty()) return 0.0;
        double mean = mean();
        double sumSq = 0.0;
        for (double r : this.returns) sumSq += Math.pow(r - mean, 2);
        return sumSq / this.returns.size();
    }

    public double volatility() {
        return Math.sqrt(variance());
    }

    public double sharpeRatio() {
        double vol = volatility();
        if (vol == 0) return 0.0;
        return (mean() - this.riskFreeRate) / vol;
    }

    public double maxReturn() {
        if (this.returns.isEmpty()) return 0.0;
        double max = this.returns.get(0);
        for (double r : this.returns) if (r > max) max = r;
        return max;
    }

    public double minReturn() {
        if (this.returns.isEmpty()) return 0.0;
        double min = this.returns.get(0);
        for (double r : this.returns) if (r < min) min = r;
        return min;
    }

    public void printStats() {
        System.out.printf("Portfolio   : %s%n", this.portfolioName);
        System.out.printf("Periods     : %d%n", this.returns.size());
        System.out.printf("Mean return : %.4f%n", mean());
        System.out.printf("Variance    : %.6f%n", variance());
        System.out.printf("Volatility  : %.4f%n", volatility());
        System.out.printf("Sharpe ratio: %.4f%n", sharpeRatio());
        System.out.printf("Max return  : %.4f%n", maxReturn());
        System.out.printf("Min return  : %.4f%n", minReturn());
    }

    @Override
    public String toString() {
        return String.format(
                "PortfolioOptimiser | name=%s | n=%d | mean=%.4f | vol=%.4f",
                this.portfolioName, this.returns.size(), mean(), volatility()
        );
    }

    public static void main(String[] args) {
        Day82PortfolioOptimiser po =
                new Day82PortfolioOptimiser("Growth Fund", 0.02);
        po.addReturn(0.05);
        po.addReturn(0.03);
        po.addReturn(0.08);
        po.addReturn(0.02);
        po.addReturn(0.06);

        po.printStats();
        System.out.println();
        System.out.println(po);
    }
}