import java.util.ArrayList;

/**
 * Day 87 – Generator and itertools concepts in Java: lazy pipelines, chunking, accumulation.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day87DealPipeline {

    private String pipelineName;
    private ArrayList<Double> values;
    private ArrayList<String> sectors;

    public Day87DealPipeline(String pipelineName) {
        this.pipelineName = pipelineName;
        this.values = new ArrayList<>();
        this.sectors = new ArrayList<>();
    }

    public String getPipelineName() { return this.pipelineName; }
    public int getSize() { return this.values.size(); }

    public void add(double value, String sector) {
        this.values.add(value);
        this.sectors.add(sector);
    }

    public ArrayList<Double> runningTotal() {
        ArrayList<Double> result = new ArrayList<>();
        double total = 0.0;
        for (double v : this.values) {
            total += v;
            result.add(total);
        }
        return result;
    }

    public ArrayList<Double> aboveThreshold(double threshold) {
        ArrayList<Double> result = new ArrayList<>();
        for (double v : this.values) {
            if (v > threshold) result.add(v);
        }
        return result;
    }

    public ArrayList<ArrayList<Double>> chunked(int size) {
        ArrayList<ArrayList<Double>> result = new ArrayList<>();
        for (int i = 0; i < this.values.size(); i += size) {
            int end = Math.min(i + size, this.values.size());
            result.add(new ArrayList<>(this.values.subList(i, end)));
        }
        return result;
    }

    public ArrayList<Double> topN(int n) {
        ArrayList<Double> sorted = new ArrayList<>(this.values);
        sorted.sort((a, b) -> Double.compare(b, a));
        return new ArrayList<>(sorted.subList(0, Math.min(n, sorted.size())));
    }

    public double totalValue() {
        double total = 0.0;
        for (double v : this.values) total += v;
        return total;
    }

    public void printStats() {
        System.out.printf("Pipeline     : %s%n", this.pipelineName);
        System.out.printf("Deals        : %d%n", this.values.size());
        System.out.printf("Total value  : £%.1fm%n", totalValue());
        System.out.printf("Running total: %s%n", runningTotal());
        System.out.printf("Top 2        : %s%n", topN(2));
    }

    @Override
    public String toString() {
        return String.format(
                "DealPipeline | name=%s | deals=%d | total=£%.1fm",
                this.pipelineName, this.values.size(), totalValue()
        );
    }

    public static void main(String[] args) {
        Day87DealPipeline dp = new Day87DealPipeline("UK Portfolio");
        dp.add(80.0, "Office");
        dp.add(30.0, "Retail");
        dp.add(60.0, "Industrial");
        dp.add(50.0, "Office");

        dp.printStats();
        System.out.println("\nChunked (2): " + dp.chunked(2));
        System.out.println("Above £55m : " + dp.aboveThreshold(55.0));
        System.out.println("\n" + dp);
    }
}