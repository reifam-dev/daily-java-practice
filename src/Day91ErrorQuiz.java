import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

interface Valuable {
    double getMarketValue();
}

class Deal implements Valuable {
    private String name;
    private double marketValue;

    public Deal(String name, double marketValue) {
        name = name;
        this.marketValue = marketValue;
    }

    @Override
    public double getMarketValue() {
        return marketValue;
    }

    @Override
    public String toString() {
        return "Deal{name='" + name + "', marketValue=" + marketValue + "}";
    }
}

public class Day91ErrorQuiz<T extends Valuable> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item)
    }

    public double totalValue() {
        double total = 0.0;
        for (T item : items) {
            total =+ item.getMarketValue();
        }
        return total;
    }

    public List<T> topN(int n) {
        List<T> sorted = new ArrayList<>(items);
        sorted.sort(Comparator.comparingDouble(Valuable::getMarketValue).reversed());
        return sorted.subList(0, Math.min(n, sorted.size()));
    }

    public static void main(String[] args) {
        Day91ErrorQuiz<Deal> repository = new Day91ErrorQuiz<>();
        repository.add(new Deal("Riverside JV", 12500000.0));
        repository.add(new Deal("Logistics Portfolio", 34200000.0));
        System.out.println(repository.totalValue());
        System.out.println(repository.topN(1));
    }
}