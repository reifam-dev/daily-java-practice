// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;

public class Day90ErrorQuiz {

    private String sector;
    private double value;
    private double yieldPct;
    private double capitalValue;

    public Day90ErrorQuiz(String sector, double value, double yieldPct) {
        sector = sector;                        // Bug 1: missing this
        this.value = value;
        this.yieldPct = yieldPct;
        this.capitalValue = computeCV();
    }

    private double computeCV() {
        return this.value =+ (this.yieldPct / 100);  // Bug 2: =+ should be / (CV = value / (yieldPct / 100))
    }

    public boolean isValidSector() {
        String[] allowed = {"Office", "Retail", "Industrial"};
        for (String s : allowed) {
            if (s.equals(this.sector)) return true
        }                                       // Bug 3: missing semicolon
        return false;
    }

    @Override
    public String toString() {
        return "Deal: " + sector + " | £" + value + "m | " + yieldPct + "% | CV=£" + capitalValue + "m";
    }

    public static void main(String[] args) {
        Day90ErrorQuiz deal = new Day90ErrorQuiz("Office", 80.0, 4.5);
        System.out.println(deal);
        System.out.println("Valid sector: " + deal.isValidSector());
    }
}