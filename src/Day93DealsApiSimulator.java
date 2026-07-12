import java.util.HashMap;
import java.util.Map;

final class DealNotFoundException extends RuntimeException {
    private final int dealId;

    public DealNotFoundException(int dealId) {
        super("Deal " + dealId + " not found");
        this.dealId = dealId;
    }

    public int getDealId() {
        return this.dealId;
    }
}

final class InvestorSummary {
    private final int totalInvestors;

    public InvestorSummary(int totalInvestors) {
        this.totalInvestors = totalInvestors;
    }

    public int getTotalInvestors() {
        return this.totalInvestors;
    }
}

final class DealResponse {
    private final int id;
    private final String name;
    private final double marketValue;
    private final InvestorSummary summary;

    public DealResponse(int id, String name, double marketValue, InvestorSummary summary) {
        this.id = id;
        this.name = name;
        this.marketValue = marketValue;
        this.summary = summary;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    public InvestorSummary getSummary() {
        return this.summary;
    }

    @Override
    public String toString() {
        return "DealResponse{id=" + this.id + ", name='" + this.name + "', marketValue="
                + this.marketValue + ", investors=" + this.summary.getTotalInvestors() + '}';
    }
}

public class Day93DealsApiSimulator {
    private final Map<Integer, DealResponse> deals;
    private int notificationsSent;

    public Day93DealsApiSimulator() {
        this.deals = new HashMap<>();
        this.deals.put(1, new DealResponse(1, "Riverside JV", 12500000.0, new InvestorSummary(2)));
        this.notificationsSent = 0;
    }

    public DealResponse readDeal(int dealId) {
        DealResponse deal = this.deals.get(dealId);
        if (deal == null) {
            throw new DealNotFoundException(dealId);
        }
        return deal;
    }

    public void notifyDeal(int dealId) {
        DealResponse deal = this.deals.get(dealId);
        if (deal == null) {
            throw new DealNotFoundException(dealId);
        }
        this.notificationsSent += 1;
    }

    public int getNotificationsSent() {
        return this.notificationsSent;
    }

    @Override
    public String toString() {
        return "Day93DealsApiSimulator{deals=" + this.deals.size()
                + ", notificationsSent=" + this.notificationsSent + '}';
    }

    public static void main(String[] args) {
        Day93DealsApiSimulator api = new Day93DealsApiSimulator();
        System.out.println(api.readDeal(1));
        api.notifyDeal(1);
        System.out.println("Notifications sent: " + api.getNotificationsSent());
        try {
            api.readDeal(99);
        } catch (DealNotFoundException e) {
            System.out.println("Handled: " + e.getMessage());
        }
    }
}