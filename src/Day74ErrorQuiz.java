// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;

public class Day74ErrorQuiz {

    private String name;
    private ArrayList<Double> values;

    public Day74ErrorQuiz(String name) {
        name = name;                            // Bug 1: missing this
        this.values = new ArrayList<>();
    }

    public void addValue(double value) {
        this.values.add(value);
    }

    public double meanValue() {
        double total = 0.0;
        for (double v : this.values) {
            total =- v;                         // Bug 2: =- should be +=
        }
        return total / this.values.size();
    }

    public void printAll() {
        for (double v : this.values) {
            System.out.println(v)               // Bug 3: missing semicolon
        }
    }

    @Override
    public String toString() {
        return "API model: " + name + " | count=" + values.size();
    }

    public static void main(String[] args) {
        Day74ErrorQuiz model = new Day74ErrorQuiz("DealModel");
        model.addValue(50.0);
        model.addValue(75.0);
        model.addValue(30.0);
        System.out.println(model);
        System.out.println("Mean: " + model.meanValue());
    }
}