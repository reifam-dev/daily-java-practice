import java.util.ArrayList;
import java.util.List;

class DealRow {
    private String dealName;
    private double marketValue;
    private double ltv;

    public DealRow(String dealName, double marketValue, double ltv) {
        this.dealName = dealName;
        this.marketValue = marketValue;
        this.ltv = ltv;
    }

    public String getDealName() {
        return dealName;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public double getLtv() {
        return ltv;
    }
}

public class Day114ErrorQuiz {

    private static boolean isValid(String dealName, double marketValue, double ltv) {
        if (dealName.trim().isEmpty()) {
            return false;
        }
        if (marketValue < 0) {
            return false;
        }
        if (ltv < 0.0 && ltv > 1.0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<DealRow> validRows = new ArrayList<>();
        int rejected = 0;

        String[] names = {"Riverside JV", "", "Westgate Retail", "Docklands Logistics"};
        double[] values = {12500000.0, 34200000.0, -8100000.0, 15750000.0};
        double[] ltvs = {0.60, 0.55, 0.65, 1.20};

        for (int i = 0; i < names.length; i++) {
            if (isValid(names[i], values[i], ltvs[i])) {
                validRows.add(new DealRow(names[i], values[i], ltvs[i]));
            } else {
                rejected++
            }
        }

        System.out.println("Valid: " + validRows.size() + ", Rejected: " + rejected);
    }
}