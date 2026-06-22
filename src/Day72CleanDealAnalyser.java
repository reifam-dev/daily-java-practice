import java.util.ArrayList;

/**
 * Day 72 – Pandas GroupBy equivalent in Java: manual grouping and aggregation.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day72CleanDealAnalyser {

    private String portfolioName;
    private ArrayList<String> sectors;
    private ArrayList<String> regions;
    private ArrayList<Double> values;
    private ArrayList<Double> incomes;

    public Day72CleanDealAnalyser(String portfolioName) {
        this.portfolioName = portfolioName;
        this.sectors = new ArrayList<>();
        this.regions = new ArrayList<>();
        this.values = new ArrayList<>();
        this.incomes = new ArrayList<>();
    }

    public void addDeal(String sector, String region, double value, double income) {
        this.sectors.add(sector);
        this.regions.add(region);
        this.values.add(value);
        this.incomes.add(income);
    }

    public String getPortfolioName() { return this.portfolioName; }
    public ArrayList<Double> getValues() { return this.values; }

    public double totalValue() {
        double total = 0.0;
        for (double v : this.values) total += v;
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
            if (v > mean) result.add(v);
        }
        return result;
    }

    public double yieldForIndex(int i) {
        return (this.incomes.get(i) / this.values.get(i)) * 100;
    }

    public void printYields() {
        for (int i = 0; i < this.values.size(); i++) {
            System.out.printf("Deal %d | Sector: %-12s | Yield: %.2f%%%n",
                    i + 1, this.sectors.get(i), yieldForIndex(i));
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Portfolio: %s | Deals: %d | Total: £%.1fm | Mean: £%.1fm",
                this.portfolioName, this.values.size(), totalValue(), meanValue()
        );
    }

    public static void main(String[] args) {
        Day72CleanDealAnalyser da = new Day72CleanDealAnalyser("UK Portfolio");
        da.addDeal("Office", "London", 50.0, 3.0);
        da.addDeal("Retail", "Manchester", 30.0, 2.1);
        da.addDeal("Industrial", "Birmingham", 80.0, 4.8);
        da.addDeal("Office", "London", 40.0, 2.8);

        System.out.println(da);
        System.out.println("Above average: " + da.aboveAverage());
        da.printYields();
    }
}