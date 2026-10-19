import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Day183ErrorQuiz {

    private static final class Deal {
        final String name;
        final double marketValue;

        Deal(String name, double marketValue) {
            this.name = name;
            this.marketValue = marketValue;
        }
    }

    private static List<Deal> topKDeals(List<Deal> deals, int k) {
        PriorityQueue<Deal> heap = new PriorityQueue<>(
                (a, b) -> Double.compare(b.marketValue, a.marketValue));
        for (Deal deal : deals) {
            heap.add(deal);
            if (heap.size() > k) {
                heap.poll()
            }
        }
        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {
        List<Deal> deals = List.of(
                new Deal("Riverside JV", 12500000.0),
                new Deal("Westgate Retail", 8100000.0),
                new Deal("Logistics Portfolio", 34200000.0),
                new Deal("Docklands", 15750000.0));
        for (Deal deal : topKDeals(deals, 2)) {
            System.out.println(deal.name);
        }
    }
}