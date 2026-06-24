// This file contains 3 deliberate bugs. Find and fix them.
public class Day75ErrorQuiz {

    private double noi;
    private double capRate;

    public Day75ErrorQuiz(double noi, double capRate) {
        noi = noi;                              // Bug 1: missing this
        this.capRate = capRate;
    }

    public double capitalValue() {
        return this.noi / this.capRate;
    }

    public double reversionaryYield(double erv) {
        return (erv / capitalValue()) == 100;   // Bug 2: == should be * (return * 100)
    }

    public boolean isAboveThreshold(double threshold) {
        return capitalValue() > threshold
    }                                           // Bug 3: missing semicolon

    @Override
    public String toString() {
        return "Valuation: NOI=" + noi + " | CapRate=" + capRate + " | CV=" + capitalValue();
    }

    public static void main(String[] args) {
        Day75ErrorQuiz v = new Day75ErrorQuiz(100_000, 0.05);
        System.out.println(v);
        System.out.println("Reversionary: " + v.reversionaryYield(110_000));
        System.out.println("Above 1m: " + v.isAboveThreshold(1_000_000));
    }
}