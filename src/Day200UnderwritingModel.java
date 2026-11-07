import java.util.ArrayList;
import java.util.List;

/**
 * Milestone: a lease-by-lease underwriting model that chains the earlier
 * building blocks - leases to year-by-year NOI (voids, re-letting), to
 * acquisition and exit cash flows, to unlevered IRR, NPV and equity
 * multiple. The IRR and NPV logic is repeated here from Day 178 because
 * this flat src/ folder compiles one file at a time.
 *
 * Simplifications: a lease that ends is void for voidYears (no income, but
 * operating costs continue on the rent it WOULD earn), then re-lets at its
 * ERV; exit value is the final year's NOI over the exit cap rate.
 */
public class Day200UnderwritingModel {

    private static double npv(double rate, List<Double> flows) {
        double total = 0.0;
        for (int t = 0; t < flows.size(); t++) {
            total += flows.get(t) / Math.pow(1 + rate, t);
        }
        return total;
    }

    private static double irr(List<Double> flows) {
        double low = -0.99;
        double high = 1.0;
        if (npv(low, flows) * npv(high, flows) > 0) {
            throw new IllegalArgumentException("IRR is not bracketed");
        }
        for (int i = 0; i < 200; i++) {
            double mid = (low + high) / 2;
            double value = npv(mid, flows);
            if (Math.abs(value) < 1e-9) {
                return mid;
            }
            if (value > 0) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return (low + high) / 2;
    }

    private static double yearNoi(double[][] leases, int year, int voidYears, double opexPct) {
        double actual = 0.0;
        double potential = 0.0;
        for (double[] lease : leases) {
            double rent = lease[0];
            int endYear = (int) lease[1];
            double erv = lease[2];
            if (year <= endYear) {
                actual += rent;
                potential += rent;
            } else if (year <= endYear + voidYears) {
                potential += erv;
            } else {
                actual += erv;
                potential += erv;
            }
        }
        return actual - opexPct * potential;
    }

    private static List<Double> buildCashFlows(double[][] leases, double price, double costsPct,
                                               int holdYears, int voidYears, double opexPct,
                                               double exitCap, double sellingPct) {
        if (holdYears <= 0) {
            throw new IllegalArgumentException("holdYears must be positive");
        }
        List<Double> flows = new ArrayList<>();
        flows.add(-price * (1 + costsPct));
        List<Double> noiByYear = new ArrayList<>();
        for (int year = 1; year <= holdYears; year++) {
            noiByYear.add(yearNoi(leases, year, voidYears, opexPct));
        }
        flows.addAll(noiByYear);

        double exitValue = noiByYear.get(noiByYear.size() - 1) / exitCap;
        int last = flows.size() - 1;
        flows.set(last, flows.get(last) + exitValue * (1 - sellingPct));
        return flows;
    }

    public static void main(String[] args) {
        double[][] leases = {{250000.0, 3, 270000.0}, {180000.0, 7, 190000.0}, {120000.0, 2, 130000.0}};
        List<Double> flows = buildCashFlows(leases, 8000000.0, 0.068, 5, 1, 0.10, 0.06, 0.015);
        System.out.println(flows);
        System.out.printf("irr %.4f, npv at 8%% %.0f%n", irr(flows), npv(0.08, flows));

        double inflows = 0.0;
        for (int i = 1; i < flows.size(); i++) {
            inflows += flows.get(i);
        }
        System.out.printf("equity multiple %.4f%n", inflows / -flows.get(0));
    }
}