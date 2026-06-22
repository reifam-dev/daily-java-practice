// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;

public class Day72ErrorQuiz {

    private String name;
    private ArrayList<Double> values;
    private ArrayList<String> sectors;

    public Day72ErrorQuiz(String name) {
        name = name;                            // Bug 1: missing this
        this.values = new ArrayList<>();
        this.sectors = new ArrayList<>();
    }

    public void addDeal(String sector, double value) {
        this.sectors.add(sector);
        this.values.add(value);
    }

    public double totalValue() {
        double total = 0.0;
        for (double v : this.values) {
            total =+ v;                         // Bug 2: =+ should be +=
        }
        return total;
    }

    public double meanValue() {
        if (this.values.isEmpty()) return 0.0;
        return totalValue() / this.values.size();
    }

    public ArrayList<Double> aboveAverage() {
        double mean = meanValue();
        ArrayList<Double> result = new ArrayList<>();
        for (double v : this.values) {
            if (v > mean) result.add(v)
        }                                       // Bug 3: missing semicolon
        return result;
    }

    @Override
    public String toString() {
        return "Analyser: " + name + " | Deals: " + values.size() + " | Mean: " + String.format("%.2f", meanValue());
    }

    public static void main(String[] args) {
        Day72ErrorQuiz da = new Day72ErrorQuiz("UK Portfolio");
        da.addDeal("Office", 50.0);
        da.addDeal("Retail", 30.0);
        da.addDeal("Industrial", 80.0);
        System.out.println(da);
        System.out.println("Above average: " + da.aboveAverage());
    }
}