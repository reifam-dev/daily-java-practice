import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

interface Valuable {
    double getMarketValue();
}

final class Deal implements Valuable {
    private final String name;
    private final double marketValue;

    public Deal(String name, double marketValue) {
        this.name = name;
        this.marketValue = marketValue;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public double getMarketValue() {
        return this.marketValue;
    }

    @Override
    public String toString() {
        return "Deal{name='" + this.name + "', marketValue=" + this.marketValue + '}';
    }
}

public class Day91TypedDealRepository<T extends Valuable> {
    private final List<T> items;

    public Day91TypedDealRepository() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        this.items.add(item);
    }

    public double getTotalValue() {
        double total = 0.0;
        for (T item : this.items) {
            total += item.getMarketValue();
        }
        return total;
    }

    public List<T> getTopN(int n) {
        List<T> sorted = new ArrayList<>(this.items);
        sorted.sort(Comparator.comparingDouble(Valuable::getMarketValue).reversed());
        return sorted.subList(0, Math.min(n, sorted.size()));
    }

    @Override
    public String toString() {
        return "Day91TypedDealRepository{items=" + this.items + '}';
    }

    public static void main(String[] args) {
        Day91TypedDealRepository<Deal> repository = new Day91TypedDealRepository<>();
        repository.addItem(new Deal("Riverside JV", 12500000.0));
        repository.addItem(new Deal("Logistics Portfolio", 34200000.0));
        System.out.println("Total value: " + repository.getTotalValue());
        System.out.println("Top deal: " + repository.getTopN(1));
    }
}