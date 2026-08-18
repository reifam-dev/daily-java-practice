import java.util.HashMap;
import java.util.Map;

class Deal {
    private String dealName;
    private double marketValue;
    private int version;

    public Deal(String dealName, double marketValue, int version) {
        this.dealName = dealName;
        this.marketValue = marketValue;
        this.version = version;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public int getVersion() {
        return version;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}

public class Day130ErrorQuiz {

    private static Map<String, Deal> deals = new HashMap<>();

    static {
        deals.put("deal-1", new Deal("Riverside JV", 12500000.0, 1));
    }

    private static Deal updateDeal(String dealId, int expectedVersion, double newMarketValue) {
        Deal deal = deals.get(dealId);

        if (deal.getVersion() != expectedVersion) {
            throw new RuntimeException("Version mismatch");
        }

        deal.setMarketValue(newMarketValue);
        return deal
    }

    public static void main(String[] args) {
        int userAVersion = deals.get("deal-1").getVersion();
        int userBVersion = deals.get("deal-1").getVersion();

        Deal resultA = updateDeal("deal-1", userAVersion, 13000000.0);
        System.out.println("User A update succeeded: " + resultA.getMarketValue());

        Deal resultB = updateDeal("deal-1", userBVersion, 13500000.0);
        System.out.println("User B update succeeded: " + resultB.getMarketValue());
    }
}