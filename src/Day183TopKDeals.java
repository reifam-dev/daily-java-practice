import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * To keep the K LARGEST values, hold a MIN-heap of size K: the head is
 * the smallest of the current top K, so it is the one to evict when a
 * bigger value arrives. PriorityQueue iteration order is NOT sorted, so
 * the result is sorted largest-first explicitly.
 */
public class Day183TopKDeals {

    private static final class Deal {
        final String name;
        final double marketValue;

        Deal(String name, double marketValue) {
            this.name = name;
            this.marketValue = marketValue;
        }
    }

    private static List<Deal> topKDeals(List<Deal> deals, int k) {
        if (k <= 0) {
            return new ArrayList<>();
        }
        PriorityQueue<Deal> heap = new PriorityQueue<>(
                Comparator.comparingDouble((Deal d) -> d.marketValue));
        for (Deal deal : deals) {
            heap.add(deal);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        List<Deal> result = new ArrayList<>(heap);
        result.sort(Comparator.comparingDouble((Deal d) -> d.marketValue).reversed());
        return result;
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