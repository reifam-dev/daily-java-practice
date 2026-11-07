import java.util.ArrayList;
import java.util.List;

public class Day200ErrorQuiz {

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
            if (npv(mid, flows) > 0) {
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
        List<Double> flows = new ArrayList<>();
        flows.add(-price * (1 + costsPct));
        List<Double> noiByYear = new ArrayList<>();
        for (int year = 1; year <= holdYears; year++) {
            noiByYear.add(yearNoi(leases, year, voidYears, opexPct));
        }
        flows.addAll(noiByYear);

        double exitValue = noiByYear.get(0) / exitCap;
        int last = flows.size() - 1;
        flows.set(last, flows.get(last) + exitValue * (1 + sellingPct))
        return flows;
    }

    public static void main(String[] args) {
        double[][] leases = {{250000.0, 3, 270000.0}, {180000.0, 7, 190000.0}, {120000.0, 2, 130000.0}};
        List<Double> flows = buildCashFlows(leases, 8000000.0, 0.068, 5, 1, 0.10, 0.06, 0.015);
        System.out.println(flows);
        System.out.printf("%.4f%n", irr(flows));
    }
}