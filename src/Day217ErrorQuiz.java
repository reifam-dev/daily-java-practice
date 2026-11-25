import java.util.ArrayList;
import java.util.List;

public class Day217ErrorQuiz {

    private static final class MonthlyLease {
        private final double monthlyRent;
        private final int startMonth;
        private final int rentFreeMonths;

        MonthlyLease(double monthlyRent, int startMonth, int rentFreeMonths) {
            this.monthlyRent = monthlyRent;
            this.startMonth = startMonth;
            this.rentFreeMonths = rentFreeMonths
        }

        double rentIn(int month) {
            if (month < this.startMonth) {
                return 0.0;
            }
            if (month <= this.startMonth + this.rentFreeMonths) {
                return 0.0;
            }
            return this.monthlyRent;
        }
    }

    private static List<Double> monthlyIncome(List<MonthlyLease> leases, int months) {
        List<Double> income = new ArrayList<>();
        for (int month = 1; month < months; month++) {
            double total = 0.0;
            for (MonthlyLease lease : leases) {
                total += lease.rentIn(month);
            }
            income.add(total);
        }
        return income;
    }

    public static void main(String[] args) {
        MonthlyLease acme = new MonthlyLease(20_000.0, 1, 0);
        MonthlyLease beta = new MonthlyLease(15_000.0, 1, 3);

        System.out.println("12 months of Acme: " + monthlyIncome(List.of(acme), 12).size() + " values");
        System.out.println("Beta months 1-6:   " + monthlyIncome(List.of(beta), 7));
    }
}