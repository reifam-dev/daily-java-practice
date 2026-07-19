import java.util.ArrayList;
import java.util.List;

class Investor {
    private String name;
    private List<Deal> deals;

    public Investor(String name) {
        this.name = name;
        this.deals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void addDeal(Deal deal) {
        deals.add(deal);
    }
}

class Deal {
    private String name;
    private double marketValue;

    public Deal(String name, double marketValue) {
        this.name = name;
        this.marketValue = marketValue;
    }

    public String getName() {
        return name;
    }

    public double getMarketValue() {
        return marketValue;
    }
}

class PortfolioSummary {
    private String investorName;
    private int dealCount;
    private double totalValue;
    private double averageDealValue;

    public PortfolioSummary(String investorName, int dealCount, double totalValue, double averageDealValue) {
        this.investorName = investorName;
        this.dealCount = dealCount;
        this.totalValue = totalValue;
        this.averageDealValue = averageDealValue;
    }

    @Override
    public String toString() {
        return "PortfolioSummary{investor='" + investorName + "', deals=" + dealCount
                + ", total=" + totalValue + ", average=" + averageDealValue + "}";
    }
}

public class Day100ErrorQuiz {
    private List<Investor> investors = new ArrayList<>();

    public Investor createInvestor(String name) {
        Investor investor = new Investor(name);
        investors.add(investor)
        return investor;
    }

    public void createDeal(Investor investor, String dealName, double marketValue) {
        Deal deal = new Deal(dealName, marketValue);
        investor.addDeal(deal);
    }

    public PortfolioSummary portfolioSummary(Investor investor) {
        int dealCount = investor.getDeals().size();
        double total = 0.0;
        for (Deal deal : investor.getDeals()) {
            total =+ deal.getMarketValue();
        }
        double average = total / dealCount;
        return new PortfolioSummary(investor.getName(), dealCount, total, average);
    }

    public static void main(String[] args) {
        Day100ErrorQuiz platform = new Day100ErrorQuiz();
        Investor investor = platform.createInvestor("Fund A");
        platform.createDeal(investor, "Riverside JV", 12500000.0);
        platform.createDeal(investor, "Logistics Portfolio", 34200000.0);
        System.out.println(platform.portfolioSummary(investor));
    }
}