import java.util.ArrayList;
import java.util.Collections;

public class Day35AuctionSystem {

    public static void main(String[] args) {

        Bid b1 = new Bid("Alice", 100.0);
        Bid b2 = new Bid("Bob", 150.0);
        Bid b3 = new Bid("Charlie", 200.0);

        ArrayList<Bid> bids = new ArrayList<>();
        bids.add(b1);
        bids.add(b2);
        bids.add(b3);

        System.out.println("All bids:");
        for (Bid b : bids) {
            System.out.println("  " + b);
        }

        Collections.sort(bids);
        System.out.println("\nSorted lowest to highest:");
        for (Bid b : bids) {
            System.out.println("  " + b);
        }

        Bid highest = Collections.max(bids);
        System.out.println("\nHighest bid: " + highest);

        System.out.println("\nequals check:");
        Bid b4 = new Bid("Diana", 100.0);
        System.out.println("b1 equals b4: " + b1.equals(b4));  // true — same amount
        System.out.println("b1 equals b2: " + b1.equals(b2));  // false

    }

}

class Bid implements Comparable<Bid> {

    private String bidder;
    private double amount;

    public Bid(String bidder, double amount) {
        this.bidder = bidder;
        this.amount = amount;
    }

    public String getBidder() {
        return bidder;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Bid other) {
        return Double.compare(this.amount, other.amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Bid)) return false;
        Bid other = (Bid) obj;
        return Double.compare(this.amount, other.amount) == 0;
    }

    @Override
    public String toString() {
        return String.format("Bid(bidder='%s', amount=£%.2f)",
                bidder, amount);
    }

}
