// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;

public class Day82ErrorQuiz {

    private String portfolioName;
    private ArrayList<Double> returns;

    public Day82ErrorQuiz(String portfolioName) {
        portfolioName = portfolioName;          // Bug 1: missing this
        this.returns = new ArrayList<>();
    }

    public void addReturn(double r) {
        this.returns.add(r);
    }

    public double mean() {
        double total = 0.0;
        for (double r : this.returns) {
            total =- r;                         // Bug 2: =- should be +=
        }
        return total / this.returns.size();
    }

    public double variance() {
        double mean = mean();
        double sumSq = 0.0;
        for (double r : this.returns) {
            sumSq += Math.pow(r - mean, 2);
        }
        return sumSq / this.returns.size();
    }

    public void printStats() {
        System.out.println("Mean: " + mean());
        System.out.println("Variance: " + variance())
        System.out.println("Volatility: " + Math.sqrt(variance()));  // Bug 3: missing semicolon above
    }

    @Override
    public String toString() {
        return "Portfolio: " + portfolioName + " | n=" + returns.size();
    }

    public static void main(String[] args) {
        Day82ErrorQuiz po = new Day82ErrorQuiz("Growth Fund");
        po.addReturn(0.05);
        po.addReturn(0.03);
        po.addReturn(0.08);
        po.addReturn(0.02);
        System.out.println(po);
        po.printStats();
    }
}