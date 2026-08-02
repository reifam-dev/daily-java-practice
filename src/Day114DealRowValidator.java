import java.util.ArrayList;
import java.util.List;

final class DealRow {
    private final String dealName;
    private final double marketValue;
    private final double ltv;

    public DealRow(String dealName, double marketValue, double ltv) {
        this.dealName = dealName;
        this.marketValue = marketValue;
        this.ltv = ltv;
    }

    public String getDealName() {
        return this.dealName;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    public double getLtv() {
        return this.ltv;
    }

    @Override
    public String toString() {
        return "DealRow{name='" + this.dealName + "', marketValue=" + this.marketValue
                + ", ltv=" + this.ltv + '}';
    }
}

/**
 * Validates raw deal records against domain rules (non-empty name,
 * positive market value, LTV between 0 and 1), separating valid rows
 * from rejected ones - a Java analogue of the Pydantic-based Python
 * validator.
 */
public class Day114DealRowValidator {

    private static final double MIN_LTV = 0.0;
    private static final double MAX_LTV = 1.0;

    private static boolean isValid(String dealName, double marketValue, double ltv) {
        if (dealName.trim().isEmpty()) {
            return false;
        }
        if (marketValue <= 0) {
            return false;
        }
        return ltv >= MIN_LTV && ltv <= MAX_LTV;
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
                rejected++;
            }
        }

        System.out.println("Valid: " + validRows.size() + ", Rejected: " + rejected);
        for (DealRow row : validRows) {
            System.out.println("  " + row);
        }
    }
}