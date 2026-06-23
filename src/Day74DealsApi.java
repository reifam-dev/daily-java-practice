import java.util.ArrayList;

/**
 * Day 74 – FastAPI concepts in Java: in-memory CRUD, path/query param simulation.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day74DealsApi {

    private int id;
    private String sector;
    private String region;
    private double value;
    private double yieldPct;

    public Day74DealsApi(int id, String sector, String region, double value, double yieldPct) {
        this.id = id;
        this.sector = sector;
        this.region = region;
        this.value = value;
        this.yieldPct = yieldPct;
    }

    public int getId() { return this.id; }
    public String getSector() { return this.sector; }
    public String getRegion() { return this.region; }
    public double getValue() { return this.value; }
    public double getYieldPct() { return this.yieldPct; }

    public static ArrayList<Day74DealsApi> searchBySector(
            ArrayList<Day74DealsApi> deals, String sector, double minValue) {
        ArrayList<Day74DealsApi> result = new ArrayList<>();
        for (Day74DealsApi d : deals) {
            if (d.getSector().equals(sector) && d.getValue() >= minValue) {
                result.add(d);
            }
        }
        return result;
    }

    public static double meanYield(ArrayList<Day74DealsApi> deals) {
        if (deals.isEmpty()) return 0.0;
        double total = 0.0;
        for (Day74DealsApi d : deals) total += d.getYieldPct();
        return total / deals.size();
    }

    @Override
    public String toString() {
        return String.format(
                "Deal[id=%d | sector=%s | region=%s | value=£%.1fm | yield=%.2f%%]",
                this.id, this.sector, this.region, this.value, this.yieldPct
        );
    }

    public static void main(String[] args) {
        ArrayList<Day74DealsApi> deals = new ArrayList<>();
        deals.add(new Day74DealsApi(1, "Office", "London", 80.0, 4.5));
        deals.add(new Day74DealsApi(2, "Retail", "Manchester", 30.0, 5.5));
        deals.add(new Day74DealsApi(3, "Office", "London", 60.0, 4.8));

        for (Day74DealsApi d : deals) System.out.println(d);

        ArrayList<Day74DealsApi> offices = searchBySector(deals, "Office", 50.0);
        System.out.println("\nOffice deals above £50m:");
        for (Day74DealsApi d : offices) System.out.println(d);

        System.out.printf("%nMean yield: %.2f%%%n", meanYield(deals));
    }
}