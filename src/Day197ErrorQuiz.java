import java.util.LinkedHashMap;
import java.util.Map;

public class Day197ErrorQuiz {

    private static double paymentConstant(double annualRate, int years) {
        if (annualRate == 0.0) {
            return 1.0 / years;
        }
        double monthlyRate = annualRate / 12;
        int n = years * 12;
        return 12 * monthlyRate / (1 - Math.pow(1 + monthlyRate, -n))
    }

    private static Map<String, Double> sizeLoan(double noi, double value, double rate,
                                                int amortYears) {
        Map<String, Double> limits = new LinkedHashMap<>();
        limits.put("ltv", 0.65 * value);
        limits.put("icr", noi / 2.0 / rate);
        limits.put("dscr", noi / 1.25 / rate);
        return limits;
    }

    private static String bindingConstraint(Map<String, Double> limits) {
        String binding = null;
        double best = -Double.MAX_VALUE;
        for (Map.Entry<String, Double> entry : limits.entrySet()) {
            if (entry.getValue() > best) {
                best = entry.getValue();
                binding = entry.getKey();
            }
        }
        return binding;
    }

    public static void main(String[] args) {
        Map<String, Double> limits = sizeLoan(1000000.0, 18000000.0, 0.055, 25);
        System.out.println(limits);
        System.out.println(bindingConstraint(limits));
    }
}