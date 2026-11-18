import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * One validated, immutable model that every later module can share. A
 * required field is checked for null and for allowed values, the
 * constructor cleans the tenant name, and the collection held by Deal is
 * copied defensively with List.copyOf and exposed read-only, so a caller
 * cannot change a Deal from the outside.
 */
public class Day211DomainModels {

    private static final Set<String> SECTORS = Set.of("logistics", "retail", "office");

    private static final class Lease {
        final String tenant;
        final String sector;
        final double annualRent;

        Lease(String tenant, String sector, double annualRent) {
            if (tenant == null || tenant.trim().isEmpty()) {
                throw new IllegalArgumentException("tenant must not be blank");
            }
            Objects.requireNonNull(sector, "sector is required");
            if (!SECTORS.contains(sector)) {
                throw new IllegalArgumentException("unknown sector: " + sector);
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
            this.name = Objects.requireNonNull(name, "name is required");
            this.leases = List.copyOf(leases);
        }

        List<Lease> getLeases() {
            return this.leases;
        }

        double totalRent() {
            double total = 0.0;
            for (Lease lease : this.leases) {
                total += lease.annualRent;
            }
            return total;
        }
    }

    public static void main(String[] args) {
        try {
            new Lease("Beta Retail", null, 180000.0);
        } catch (NullPointerException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        Deal deal = new Deal("Riverside Estate", List.of(new Lease("Acme Logistics", "logistics", 250000.0)));
        System.out.println(deal.totalRent());
        try {
            deal.getLeases().add(new Lease("Injected", "retail", 999999.0));
        } catch (UnsupportedOperationException e) {
            System.out.println("Rejected: the deal's lease list is read-only");
        }
        System.out.println(deal.totalRent());
    }
}