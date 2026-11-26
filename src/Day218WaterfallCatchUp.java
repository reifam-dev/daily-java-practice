import java.util.ArrayList;
import java.util.List;

/**
 * A real waterfall runs period by period and carries state, and it has a
 * fourth tier between the preferred return and the residual split: the GP
 * catch-up, where the GP takes the cash until its share of the profit
 * distributed so far reaches the carry percentage. Three traps: the
 * preferred return accrues on UNRETURNED capital, so it shrinks as capital
 * comes back; the catch-up target is a share of TOTAL profit distributed,
 * not of the LP's profit; and once the catch-up is met the residual splits
 * at the carry rate, not at the catch-up rate.
 */
public class Day218WaterfallCatchUp {

    private static final class Terms {
        private final double committedCapital;
        private final double prefRate;
        private final double carryPct;

        Terms(double committedCapital, double prefRate, double carryPct) {
            if (committedCapital <= 0.0) {
                throw new IllegalArgumentException("committedCapital must be positive");
            }
            if (carryPct < 0.0 || carryPct >= 1.0) {
                throw new IllegalArgumentException("carryPct must be at least 0 and below 1");
            }
            this.committedCapital = committedCapital;
            this.prefRate = prefRate;
            this.carryPct = carryPct;
        }
    }

    private static final class State {
        private double unreturnedCapital;
        private double accruedPref;
        private double lpProfitPaid;
        private double gpProfitPaid;

        State(double unreturnedCapital) {
            this.unreturnedCapital = unreturnedCapital;
        }

        double profitPaid() {
            return this.lpProfitPaid + this.gpProfitPaid;
        }
    }

    private static final class Distribution {
        private final int period;
        private final double cash;
        private double returnOfCapital;
        private double preferred;
        private double catchUp;
        private double lpResidual;
        private double gpResidual;
        private final List<String> tiers = new ArrayList<>();

        Distribution(int period, double cash) {
            this.period = period;
            this.cash = cash;
        }

        double lpTotal() {
            return this.returnOfCapital + this.preferred + this.lpResidual;
        }

        double gpTotal() {
            return this.catchUp + this.gpResidual;
        }
    }

    private static double catchUpDue(State state, double carryPct) {
        double shortfall = carryPct * state.profitPaid() - state.gpProfitPaid;
        if (shortfall <= 0.0) {
            return 0.0;
        }
        return shortfall / (1.0 - carryPct);
    }

    private static Distribution distribute(State state, Terms terms, int period, double cash) {
        if (cash < 0.0) {
            throw new IllegalArgumentException("a distribution cannot be negative");
        }
        state.accruedPref += state.unreturnedCapital * terms.prefRate;
        Distribution result = new Distribution(period, cash);
        double remaining = cash;

        double paid = Math.min(remaining, state.unreturnedCapital);
        if (paid > 0.0) {
            result.returnOfCapital = paid;
            state.unreturnedCapital -= paid;
            remaining -= paid;
            result.tiers.add("return of capital");
        }

        paid = Math.min(remaining, state.accruedPref);
        if (paid > 0.0) {
            result.preferred = paid;
            state.accruedPref -= paid;
            state.lpProfitPaid += paid;
            remaining -= paid;
            result.tiers.add("preferred");
        }

        paid = Math.min(remaining, catchUpDue(state, terms.carryPct));
        if (paid > 0.0) {
            result.catchUp = paid;
            state.gpProfitPaid += paid;
            remaining -= paid;
            result.tiers.add("catch-up");
        }

        if (remaining > 0.0) {
            result.gpResidual = remaining * terms.carryPct;
            result.lpResidual = remaining - result.gpResidual;
            state.gpProfitPaid += result.gpResidual;
            state.lpProfitPaid += result.lpResidual;
            result.tiers.add("residual split");
        }
        return result;
    }

    public static void main(String[] args) {
        Terms terms = new Terms(10_000_000.0, 0.08, 0.20);
        State state = new State(terms.committedCapital);
        double[] cashflows = {2_000_000.0, 9_000_000.0, 3_000_000.0, 4_000_000.0};

        double lp = 0.0;
        double gp = 0.0;
        for (int i = 0; i < cashflows.length; i++) {
            Distribution result = distribute(state, terms, i + 1, cashflows[i]);
            lp += result.lpTotal();
            gp += result.gpTotal();
            System.out.printf("period %d: cash %,10.0f LP %,10.0f GP %,9.0f %s%n",
                    result.period, result.cash, result.lpTotal(), result.gpTotal(), result.tiers);
        }
        System.out.printf("LP total %,.0f, GP total %,.0f, sum %,.0f%n", lp, gp, lp + gp);
        System.out.printf("GP share of profit %.2f%%%n",
                gp / (gp + lp - terms.committedCapital) * 100);

        try {
            new Terms(10_000_000.0, 0.08, 1.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}