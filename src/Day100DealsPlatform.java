import java.util.ArrayList;
import java.util.List;

final class Investor {
    private final String name;
    private final List<Deal> deals;

    public Investor(String name) {
        this.name = name;
        this.deals = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Deal> getDeals() {
        return this.deals;
    }

    public void addDeal(Deal deal) {
        this.deals.add(deal);
    }

    @Override
    public String toString() {
        return "Investor{name='" + this.name + "', deals=" + this.deals.size() + '}';
    }
}

final class Deal {
    private final String name;
    private final double marketValue;

    public Deal(String name, double marketValue) {
        this.name = name;
        this.marketValue = marketValue;
    }

    public String getName() {
        return this.name;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    @Override
    public String toString() {
        return "Deal{name='" + this.name + "', marketValue=" + this.marketValue + '}';
    }
}

final class PortfolioSummary {
    private final String investorName;
    private final int dealCount;
    private final double totalValue;
    private final double averageDealValue;

    public PortfolioSummary(String investorName, int dealCount, double totalValue, double averageDealValue) {
        this.investorName = investorName;
        this.dealCount = dealCount;
        this.totalValue = totalValue;
        this.averageDealValue = averageDealValue;
    }

    @Override
    public String toString() {
        return "PortfolioSummary{investor='" + this.investorName + "', deals=" + this.dealCount
                + ", total=" + this.totalValue + ", average=" + this.averageDealValue + '}';
    }
}

/**
 * In-memory simulation of the Deals Platform API (Java analogue of the
 * Python FastAPI + SQLAlchemy milestone service).
 */
public class Day100DealsPlatform {
    private final List<Investor> investors;

    public Day100DealsPlatform() {
        this.investors = new ArrayList<>();
    }

    public Investor createInvestor(String name) {
        Investor investor = new Investor(name);
        this.investors.add(investor);
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
            total += deal.getMarketValue();
        }
        double average = dealCount == 0 ? 0.0 : total / dealCount;
        return new PortfolioSummary(investor.getName(), dealCount, total, average);
    }

    @Override
    public String toString() {
        return "Day100DealsPlatform{investors=" + this.investors.size() + '}';
    }

    public static void main(String[] args) {
        Day100DealsPlatform platform = new Day100DealsPlatform();
        Investor investor = platform.createInvestor("Fund A");
        platform.createDeal(investor, "Riverside JV", 12500000.0);
        platform.createDeal(investor, "Logistics Portfolio", 34200000.0);
        System.out.println(platform.portfolioSummary(investor));
    }
}