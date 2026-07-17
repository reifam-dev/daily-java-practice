import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Deal {
    private String name;
    private String region;
    private String sector;
    private double marketValue;

    public Deal(String name, String region, String sector, double marketValue) {
        name = name;
        this.region = region;
        this.sector = sector;
        this.marketValue = marketValue;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getSector() {
        return sector;
    }

    public double getMarketValue() {
        return marketValue;
    }
}

public class Day98ErrorQuiz {
    private List<Deal> deals;

    public Day98ErrorQuiz(List<Deal> deals) {
        this.deals = deals;
    }

    public double totalValue() {
        double total = 0.0;
        for (Deal deal : deals) {
            total =+ deal.getMarketValue();
        }
        return total;
    }

    public Map<String, List<Deal>> groupByRegion() {
        return deals.stream().collect(Collectors.groupingBy(Deal::getRegion));
    }

    public String topDealByRegion(String region) {
        return groupByRegion().get(region).stream()
                .max(Comparator.comparingDouble(Deal::getMarketValue))
                .map(Deal::getName)
                .orElse(null)
    }

    public List<Deal> highValueDeals(double threshold) {
        return deals.stream()
                .filter(deal -> deal.getMarketValue() < threshold)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Deal> deals = new ArrayList<>(List.of(
                new Deal("Riverside JV", "London", "Residential", 12500000.0),
                new Deal("Logistics Portfolio", "Midlands", "Logistics", 34200000.0)
        ));
        Day98ErrorQuiz analyser = new Day98ErrorQuiz(deals);
        System.out.println(analyser.totalValue());
        System.out.println(analyser.topDealByRegion("London"));
        System.out.println(analyser.highValueDeals(20000000.0));
    }
}