import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A lender sizes the loan three ways and lends the LOWEST: LTV caps the
 * loan at a share of value; ICR caps interest at NOI / min ICR, so loan =
 * that interest / rate; DSCR caps total debt service (interest AND
 * principal) at NOI / min DSCR, so loan = that service / the amortising
 * payment constant. The tightest test is the binding constraint.
 */
public class Day197DebtSizing {

    private static double paymentConstant(double annualRate, int years) {
        if (annualRate == 0.0) {
            return 1.0 / years;
        }
        double monthlyRate = annualRate / 12;
        int n = years * 12;
        return 12 * monthlyRate / (1 - Math.pow(1 + monthlyRate, -n));
    }

    private static Map<String, Double> sizeLoan(double noi, double value, double rate,
                                                int amortYears) {
        if (noi <= 0 || value <= 0 || rate <= 0 || amortYears <= 0) {
            throw new IllegalArgumentException("noi, value, rate and amortYears must be positive");
        }
        Map<String, Double> limits = new LinkedHashMap<>();
        limits.put("ltv", 0.65 * value);
        limits.put("icr", noi / 2.0 / rate);
        limits.put("dscr", noi / 1.25 / paymentConstant(rate, amortYears));
        return limits;
    }

    private static String bindingConstraint(Map<String, Double> limits) {
        String binding = null;
        double lowest = Double.MAX_VALUE;
        for (Map.Entry<String, Double> entry : limits.entrySet()) {
            if (entry.getValue() < lowest) {
                lowest = entry.getValue();
                binding = entry.getKey();
            }
        }
        return binding;
    }

    public static void main(String[] args) {
        Map<String, Double> limits = sizeLoan(1000000.0, 18000000.0, 0.055, 25);
        String binding = bindingConstraint(limits);
        System.out.println(limits);
        System.out.printf("max loan %.0f, binding: %s%n", limits.get(binding), binding);
    }
}