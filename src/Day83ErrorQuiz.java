// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;

public class Day83ErrorQuiz {

    private String seriesName;
    private ArrayList<Double> returns;

    public Day83ErrorQuiz(String seriesName) {
        seriesName = seriesName;                // Bug 1: missing this
        this.returns = new ArrayList<>();
    }

    public void addReturn(double r) {
        this.returns.add(r);
    }

    public double cumulativeReturn() {
        double product = 1.0;
        for (double r : this.returns) {
            product =* (1 + r);                 // Bug 2: =* should be *=
        }
        return product - 1;
    }

    public double annualisedReturn(int periodsPerYear) {
        double cumulative = cumulativeReturn();
        int n = this.returns.size();
        return Math.pow(1 + cumulative, periodsPerYear / n) - 1
    }                                           // Bug 3: missing semicolon

    @Override
    public String toString() {
        return "TimeSeries: " + seriesName + " | n=" + returns.size();
    }

    public static void main(String[] args) {
        Day83ErrorQuiz ts = new Day83ErrorQuiz("Office Returns");
        ts.addReturn(0.04);
        ts.addReturn(0.03);
        ts.addReturn(0.05);
        ts.addReturn(0.02);
        System.out.println(ts);
        System.out.println("Cumulative: " + ts.cumulativeReturn());
        System.out.println("Annualised: " + ts.annualisedReturn(12));
    }
}