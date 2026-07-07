// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;

public class Day87ErrorQuiz {

    private String pipelineName;
    private ArrayList<Double> values;

    public Day87ErrorQuiz(String pipelineName) {
        pipelineName = pipelineName;            // Bug 1: missing this
        this.values = new ArrayList<>();
    }

    public void add(double value) {
        this.values.add(value);
    }

    public ArrayList<Double> runningTotal() {
        ArrayList<Double> result = new ArrayList<>();
        double total = 0.0;
        for (double v : this.values) {
            total =+ v;                         // Bug 2: =+ should be +=
            result.add(total);
        }
        return result;
    }

    public ArrayList<Double> aboveThreshold(double threshold) {
        ArrayList<Double> result = new ArrayList<>();
        for (double v : this.values) {
            if (v > threshold) result.add(v)
        }                                       // Bug 3: missing semicolon
        return result;
    }

    @Override
    public String toString() {
        return "Pipeline: " + pipelineName + " | n=" + values.size();
    }

    public static void main(String[] args) {
        Day87ErrorQuiz dp = new Day87ErrorQuiz("UK Deals");
        dp.add(80.0); dp.add(30.0); dp.add(60.0); dp.add(50.0);
        System.out.println(dp);
        System.out.println("Running total: " + dp.runningTotal());
        System.out.println("Above 50: " + dp.aboveThreshold(50.0));
    }
}