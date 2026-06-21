// This file contains 3 deliberate bugs. Find and fix them.
public class Day71ErrorQuiz {

    private String portfolioName;
    private double[] returns;
    private int size;

    public Day71ErrorQuiz(String portfolioName, double[] returns) {
        portfolioName = portfolioName;          // Bug 1: missing this
        this.returns = returns;
        this.size = returns.length;
    }

    public double meanReturn() {
        double sum = 0.0;
        for (double r : returns) {
            sum += r;
        }
        return sum / size;
    }

    public double volatility() {
        double mean = meanReturn();
        double sumSq = 0.0;
        for (double r : returns) {
            sumSq =+ Math.pow(r - mean, 2);    // Bug 2: =+ should be +=
        }
        return Math.sqrt(sumSq / size);
    }

    public double[] aboveThreshold(double threshold) {
        int count = 0;
        for (double r : returns) {
            if (r > threshold) count++;
        }
        double[] result = new double[count];
        int idx = 0;
        for (double r : returns) {
            if (r > threshold) result[idx++] = r;
        }
        return result
    }                                           // Bug 3: missing semicolon after return statement

    @Override
    public String toString() {
        return "Portfolio: " + portfolioName + ", Size: " + size + ", Mean: " + String.format("%.4f", meanReturn());
    }

    public static void main(String[] args) {
        double[] returns = {0.05, 0.12, -0.03, 0.08, 0.15, -0.02, 0.09};
        Day71ErrorQuiz p = new Day71ErrorQuiz("Growth Fund", returns);
        System.out.println(p);
        System.out.println("Volatility: " + p.volatility());
    }
}