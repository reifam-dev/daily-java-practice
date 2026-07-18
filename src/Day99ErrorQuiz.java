import java.util.ArrayList;
import java.util.List;

class Investor {
    private String name;
    private List<Deal> deals;

    public Investor(String name) {
        name = name;
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

public class Day99ErrorQuiz {
    private List<Investor> investors = new ArrayList<>();

    public void addInvestorWithDeal(String investorName, String dealName, double marketValue) {
        Investor investor = new Investor(investorName);
        Deal deal = new Deal(dealName, marketValue);
        investor.addDeal(deal)
        investors.add(investor);
    }

    public double getInvestorTotal(String investorName) {
        double total = 0.0;
        for (Investor investor : investors) {
            if (investor.getName().equals(investorName)) {
                for (Deal deal : investor.getDeals()) {
                    total =+ deal.getMarketValue();
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Day99ErrorQuiz repository = new Day99ErrorQuiz();
        repository.addInvestorWithDeal("Fund A", "Riverside JV", 12500000.0);
        System.out.println(repository.getInvestorTotal("Fund A"));
    }
}