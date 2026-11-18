import java.util.ArrayList;
import java.util.List;

public class Day211ErrorQuiz {

    private static final class Lease {
        final String tenant;
        final String sector;
        final double annualRent;

        Lease(String tenant, String sector, double annualRent) {
            if (tenant == null || tenant.trim().isEmpty()) {
                throw new IllegalArgumentException("tenant must not be blank");
            }
            if (annualRent <= 0) {
                throw new IllegalArgumentException("annualRent must be positive");
            }
            this.tenant = tenant.trim();
            this.sector = sector;
            this.annualRent = annualRent;
        }
    }

    private static final class Deal {
        private final String name;
        private final List<Lease> leases;

        Deal(String name, List<Lease> leases) {
            this.name = name;
            this.leases = new ArrayList<>(leases);
        }

        List<Lease> getLeases() {
            return leases;
        }

        double totalRent() {
            double total = 0.0;
            for (Lease lease : leases) {
                total += lease.annualRent;
            }
            return total
        }
    }

    public static void main(String[] args) {
        Lease noSector = new Lease("Beta Retail", null, 180000.0);
        System.out.println("sector accepted as: " + noSector.sector);

        Deal deal = new Deal("Riverside Estate", List.of(new Lease("Acme Logistics", "logistics", 250000.0)));
        System.out.println(deal.totalRent());
        deal.getLeases().add(new Lease("Injected", "retail", 999999.0));
        System.out.println(deal.totalRent());
    }
}