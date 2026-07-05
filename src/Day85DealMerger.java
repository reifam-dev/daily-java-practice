import java.util.ArrayList;
import java.util.HashMap;

/**
 * Day 85 – Pandas merge/join concepts in Java: manual join simulation, grouping.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, HashMap, toString override.
 */
public class Day85DealMerger {

    private String portfolioName;
    private ArrayList<HashMap<String, String>> deals;
    private ArrayList<HashMap<String, String>> valuations;

    public Day85DealMerger(String portfolioName) {
        this.portfolioName = portfolioName;
        this.deals = new ArrayList<>();
        this.valuations = new ArrayList<>();
    }

    public String getPortfolioName() { return this.portfolioName; }
    public int getDealCount() { return this.deals.size(); }

    public void addDeal(String dealId, String sector, String region, double value) {
        HashMap<String, String> row = new HashMap<>();
        row.put("deal_id", dealId);
        row.put("sector", sector);
        row.put("region", region);
        row.put("value", String.valueOf(value));
        this.deals.add(row);
    }

    public void addValuation(String dealId, double yieldPct, double capitalValue) {
        HashMap<String, String> row = new HashMap<>();
        row.put("deal_id", dealId);
        row.put("yield_pct", String.valueOf(yieldPct));
        row.put("capital_value", String.valueOf(capitalValue));
        this.valuations.add(row);
    }

    public ArrayList<HashMap<String, String>> innerJoin() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (HashMap<String, String> deal : this.deals) {
            for (HashMap<String, String> val : this.valuations) {
                if (deal.get("deal_id").equals(val.get("deal_id"))) {
                    HashMap<String, String> merged = new HashMap<>(deal);
                    merged.putAll(val);
                    result.add(merged);
                }
            }
        }
        return result;
    }

    public ArrayList<HashMap<String, String>> filterBySector(String sector) {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (HashMap<String, String> deal : this.deals) {
            if (deal.get("sector").equals(sector)) result.add(deal);
        }
        return result;
    }

    public double meanValueBySector(String sector) {
        ArrayList<HashMap<String, String>> filtered = filterBySector(sector);
        if (filtered.isEmpty()) return 0.0;
        double total = 0.0;
        for (HashMap<String, String> row : filtered) {
            total += Double.parseDouble(row.getOrDefault("value", "0"));
        }
        return total / filtered.size();
    }

    public void printJoined() {
        System.out.printf("%-8s %-12s %-14s %-8s %-8s%n",
                "ID", "Sector", "Region", "Value", "Yield");
        System.out.println("-".repeat(55));
        for (HashMap<String, String> row : innerJoin()) {
            System.out.printf("%-8s %-12s %-14s %-8s %-8s%n",
                    row.get("deal_id"), row.get("sector"), row.get("region"),
                    row.get("value"), row.get("yield_pct"));
        }
    }

    @Override
    public String toString() {
        return String.format(
                "DealMerger | portfolio=%s | deals=%d | valuations=%d",
                this.portfolioName, this.deals.size(), this.valuations.size()
        );
    }

    public static void main(String[] args) {
        Day85DealMerger dm = new Day85DealMerger("UK Portfolio");
        dm.addDeal("1", "Office", "London", 80.0);
        dm.addDeal("2", "Retail", "Manchester", 30.0);
        dm.addDeal("3", "Industrial", "Birmingham", 60.0);
        dm.addDeal("4", "Office", "London", 50.0);
        dm.addValuation("1", 4.5, 80.0);
        dm.addValuation("2", 5.5, 30.0);
        dm.addValuation("3", 5.0, 60.0);

        dm.printJoined();
        System.out.println("\nOffice mean value: £" + dm.meanValueBySector("Office") + "m");
        System.out.println("\n" + dm);
    }
}