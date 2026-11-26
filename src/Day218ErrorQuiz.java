public class Day218ErrorQuiz {

    private static final class Terms {
        private final double committedCapital;
        private final double prefRate;
        private final double carryPct;

        Terms(double committedCapital, double prefRate, double carryPct) {
            this.committedCapital = committedCapital;
            this.prefRate = prefRate;
            this.carryPct = carryPct
        }
    }

    private static final class State {
        private double unreturnedCapital;
        private double accruedPref;
        private double lpProfitPaid;
        private double gpProfitPaid;

        State(double unreturnedCapital, double lpProfitPaid, double gpProfitPaid) {
            this.unreturnedCapital = unreturnedCapital;
            this.lpProfitPaid = lpProfitPaid;
            this.gpProfitPaid = gpProfitPaid;
        }

        double profitPaid() {
            return this.lpProfitPaid + this.gpProfitPaid;
        }
    }

    private static double accruePref(State state, Terms terms) {
        state.accruedPref += terms.committedCapital * terms.prefRate;
        return state.accruedPref;
    }

    private static double catchUpDue(State state, double carryPct) {
        double shortfall = carryPct * state.lpProfitPaid - state.gpProfitPaid;
        if (shortfall <= 0.0) {
            return 0.0;
        }
        return shortfall / (1.0 - carryPct);
    }

    public static void main(String[] args) {
        Terms terms = new Terms(10_000_000.0, 0.08, 0.20);

        State partReturned = new State(6_000_000.0, 0.0, 0.0);
        System.out.printf("pref accrued on 6m unreturned: %,.0f%n", accruePref(partReturned, terms));

        State midCatchUp = new State(0.0, 800_000.0, 100_000.0);
        System.out.printf("catch-up still due: %,.0f%n", catchUpDue(midCatchUp, terms.carryPct));
        System.out.printf("profit paid so far: %,.0f%n", midCatchUp.profitPaid());
    }
}