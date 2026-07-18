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

/**
 * In-memory simulation of an ORM-style investor/deal repository
 * (a Java analogue of the SQLAlchemy relationship pattern).
 */
public class Day99DealRepository {
    private final List<Investor> investors;

    public Day99DealRepository() {
        this.investors = new ArrayList<>();
    }

    public void addInvestorWithDeal(String investorName, String dealName, double marketValue) {
        Investor investor = new Investor(investorName);
        Deal deal = new Deal(dealName, marketValue);
        investor.addDeal(deal);
        this.investors.add(investor);
    }

    public double getInvestorTotal(String investorName) {
        double total = 0.0;
        for (Investor investor : this.investors) {
            if (investor.getName().equals(investorName)) {
                for (Deal deal : investor.getDeals()) {
                    total += deal.getMarketValue();
                }
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Day99DealRepository{investors=" + this.investors.size() + '}';
    }

    public static void main(String[] args) {
        Day99DealRepository repository = new Day99DealRepository();
        repository.addInvestorWithDeal("Fund A", "Riverside JV", 12500000.0);
        System.out.println(repository.getInvestorTotal("Fund A"));
    }
}