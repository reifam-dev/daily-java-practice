import java.util.HashMap;
import java.util.Map;

class DealNotFoundException extends RuntimeException {
    private int dealId;

    public DealNotFoundException(int dealId) {
        dealId = dealId;
    }

    public int getDealId() {
        return dealId;
    }
}

class InvestorSummary {
    private int totalInvestors;

    public InvestorSummary(int totalInvestors) {
        this.totalInvestors = totalInvestors;
    }

    public int getTotalInvestors() {
        return totalInvestors;
    }
}

class DealResponse {
    private int id;
    private String name;
    private double marketValue;
    private InvestorSummary summary;

    public DealResponse(int id, String name, double marketValue, InvestorSummary summary) {
        this.id = id;
        this.name = name;
        this.marketValue = marketValue;
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "DealResponse{id=" + id + ", name='" + name + "', marketValue=" + marketValue
                + ", investors=" + summary.getTotalInvestors() + "}";
    }
}

public class Day93ErrorQuiz {
    private Map<Integer, DealResponse> deals = new HashMap<>();
    private int notificationsSent;

    public Day93ErrorQuiz() {
        deals.put(1, new DealResponse(1, "Riverside JV", 12500000.0, new InvestorSummary(2)));
        notificationsSent = 0;
    }

    public DealResponse readDeal(int dealId) {
        DealResponse deal = deals.get(dealId);
        if (deal == null) {
            throw new DealNotFoundException(dealId);
        }
        return deal;
    }

    public void notifyDeal(int dealId) {
        DealResponse deal = deals.get(dealId)
        if (deal == null) {
            throw new DealNotFoundException(dealId);
        }
        notificationsSent =+ 1;
    }

    public static void main(String[] args) {
        Day93ErrorQuiz api = new Day93ErrorQuiz();
        System.out.println(api.readDeal(1));
        api.notifyDeal(1);
        System.out.println("Notifications sent: " + api.notificationsSent);
    }
}