import java.util.ArrayList;
import java.util.List;

/**
 * An annual model hides what happens inside a year: rent-free periods and
 * leases starting mid-year. Monthly periods fix that and bring three traps.
 * A rent-free month pays nothing, so the free-month count is a boundary and
 * off by one is the usual error. A lease starting in month 7 pays from
 * month 7 onward, not from month 1. And an IRR taken from monthly flows is
 * a MONTHLY rate: it annualises by compounding, Math.pow(1 + r, 12) - 1,
 * never by multiplying by 12.
 */
public class Day217MonthlyCashFlow {

    private static final class MonthlyLease {
        private final String tenant;
        private final double monthlyRent;
        private final int startMonth;
        private final int rentFreeMonths;

        MonthlyLease(String tenant, double monthlyRent, int startMonth, int rentFreeMonths) {
            if (startMonth < 1) {
                throw new IllegalArgumentException("startMonth is 1-based, so it must be at least 1");
            }
            if (monthlyRent <= 0.0) {
                throw new IllegalArgumentException("monthlyRent must be positive");
            }
            this.tenant = tenant;
            this.monthlyRent = monthlyRent;
            this.startMonth = startMonth;
            this.rentFreeMonths = rentFreeMonths;
        }

        double rentIn(int month) {
            if (month < this.startMonth) {
                return 0.0;
            }
            if (month < this.startMonth + this.rentFreeMonths) {
                return 0.0;
            }
            return this.monthlyRent;
        }
    }

    private static List<Double> monthlyIncome(List<MonthlyLease> leases, int months) {
        List<Double> income = new ArrayList<>();
        for (int month = 1; month <= months; month++) {
            double total = 0.0;
            for (MonthlyLease lease : leases) {
                total += lease.rentIn(month);
            }
            income.add(total);
        }
        return income;
    }

    private static double annualise(double monthlyRate) {
        return Math.pow(1.0 + monthlyRate, 12) - 1.0;
    }

    public static void main(String[] args) {
        List<MonthlyLease> leases = List.of(
                new MonthlyLease("Acme Logistics", 20_000.0, 1, 0),
                new MonthlyLease("Beta Retail", 15_000.0, 1, 3),
                new MonthlyLease("Gamma Office", 10_000.0, 7, 0));

        List<Double> income = monthlyIncome(leases, 12);
        System.out.println("months 1-3:   " + income.subList(0, 3));
        System.out.println("months 4-6:   " + income.subList(3, 6));
        System.out.println("months 7-9:   " + income.subList(6, 9));

        double yearOne = 0.0;
        for (double value : income) {
            yearOne += value;
        }
        System.out.printf("year 1 total: %,.0f%n", yearOne);
        System.out.printf("annualised 1%% a month: compounded %.2f%%, times 12 %.2f%%%n",
                annualise(0.01) * 100, 0.01 * 12 * 100);

        try {
            new MonthlyLease("Bad", 1_000.0, 0, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}