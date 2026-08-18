import java.util.HashMap;
import java.util.Map;

final class Deal {
    private final String dealName;
    private double marketValue;
    private int version;

    public Deal(String dealName, double marketValue, int version) {
        this.dealName = dealName;
        this.marketValue = marketValue;
        this.version = version;
    }

    public String getDealName() {
        return this.dealName;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    public int getVersion() {
        return this.version;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public void incrementVersion() {
        this.version += 1;
    }
}

final class StaleVersionException extends RuntimeException {
    public StaleVersionException(String message) {
        super(message);
    }
}

/**
 * Each deal carries a version number; an update must supply the
 * version it last read, and is rejected if another writer has since
 * changed the record - preventing a lost-update race condition.
 */
public class Day130OptimisticLocking {

    private final Map<String, Deal> deals;

    public Day130OptimisticLocking() {
        this.deals = new HashMap<>();
        this.deals.put("deal-1", new Deal("Riverside JV", 12500000.0, 1));
    }

    public Deal updateDeal(String dealId, int expectedVersion, double newMarketValue) {
        Deal deal = this.deals.get(dealId);

        if (deal.getVersion() != expectedVersion) {
            throw new StaleVersionException(
                    "Version mismatch: expected " + expectedVersion
                            + ", deal is at " + deal.getVersion());
        }

        deal.setMarketValue(newMarketValue);
        deal.incrementVersion();
        return deal;
    }

    public static void main(String[] args) {
        Day130OptimisticLocking service = new Day130OptimisticLocking();

        int userAVersion = service.deals.get("deal-1").getVersion();
        int userBVersion = service.deals.get("deal-1").getVersion();

        Deal resultA = service.updateDeal("deal-1", userAVersion, 13000000.0);
        System.out.println("User A update succeeded: " + resultA.getMarketValue());

        try {
            Deal resultB = service.updateDeal("deal-1", userBVersion, 13500000.0);
            System.out.println("User B update succeeded: " + resultB.getMarketValue());
        } catch (StaleVersionException e) {
            System.out.println("User B update rejected: " + e.getMessage());
        }
    }
}