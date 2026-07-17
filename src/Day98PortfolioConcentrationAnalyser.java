import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class Deal {
    private final String name;
    private final String region;
    private final String sector;
    private final double marketValue;

    public Deal(String name, String region, String sector, double marketValue) {
        this.name = name;
        this.region = region;
        this.sector = sector;
        this.marketValue = marketValue;
    }

    public String getName() {
        return this.name;
    }

    public String getRegion() {
        return this.region;
    }

    public String getSector() {
        return this.sector;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    @Override
    public String toString() {
        return "Deal{name='" + this.name + "', region='" + this.region
                + "', sector='" + this.sector + "', marketValue=" + this.marketValue + '}';
    }
}

/**
 * Groups deals by region and sector (a Java analogue of a pandas
 * MultiIndex groupby), computing concentration and exposure figures.
 */
public class Day98PortfolioConcentrationAnalyser {
    private final List<Deal> deals;

    public Day98PortfolioConcentrationAnalyser(List<Deal> deals) {
        this.deals = deals;
    }

    public double totalValue() {
        double total = 0.0;
        for (Deal deal : this.deals) {
            total += deal.getMarketValue();
        }
        return total;
    }

    public Map<String, List<Deal>> groupByRegion() {
        return this.deals.stream().collect(Collectors.groupingBy(Deal::getRegion));
    }

    public Map<String, Double> sectorExposure() {
        return this.deals.stream().collect(
                Collectors.groupingBy(Deal::getSector,
                        Collectors.summingDouble(Deal::getMarketValue)));
    }

    public String topDealByRegion(String region) {
        List<Deal> regionalDeals = groupByRegion().get(region);
        if (regionalDeals == null) {
            return null;
        }
        return regionalDeals.stream()
                .max(Comparator.comparingDouble(Deal::getMarketValue))
                .map(Deal::getName)
                .orElse(null);
    }

    public List<Deal> highValueDeals(double threshold) {
        return this.deals.stream()
                .filter(deal -> deal.getMarketValue() > threshold)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Day98PortfolioConcentrationAnalyser{deals=" + this.deals.size() + '}';
    }

    public static void main(String[] args) {
        List<Deal> deals = new ArrayList<>(List.of(
                new Deal("Riverside JV", "London", "Residential", 12500000.0),
                new Deal("Logistics Portfolio", "Midlands", "Logistics", 34200000.0),
                new Deal("Westgate Retail", "London", "Retail", 8100000.0),
                new Deal("Midtown Office", "London", "Office", 21000000.0),
                new Deal("Docklands Logistics", "Midlands", "Logistics", 15750000.0)
        ));
        Day98PortfolioConcentrationAnalyser analyser = new Day98PortfolioConcentrationAnalyser(deals);
        System.out.println("Total value: " + analyser.totalValue());
        System.out.println("Top deal in London: " + analyser.topDealByRegion("London"));
        System.out.println("Sector exposure: " + analyser.sectorExposure());
        System.out.println("High value deals: " + analyser.highValueDeals(20000000.0));
    }
}