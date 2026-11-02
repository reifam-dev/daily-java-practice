import java.util.ArrayList;
import java.util.List;

public class Day195ErrorQuiz {

    private static final String[] LINES = {
            "tenant,sector,annual_rent,lease_end_month",
            "Acme Logistics,logistics,250000,48",
            "Beta Retail,retail,abc,24",
            "Gamma Office,office,180000,36",
            ",office,90000,12",
            "Delta Office,office,90000,"};

    public static void main(String[] args) {
        List<String> valid = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (int i = 1; i < LINES.length; i++) {
            String[] fields = LINES[i].split(",");
            try {
                String tenant = fields[0].trim();
                if (tenant.isEmpty()) {
                    throw new IllegalArgumentException("tenant is blank");
                }
                double annualRent = Double.parseDouble(fields[2]);
                int leaseEndMonth = Integer.parseInt(fields[3]);
                valid.add(tenant + " " + annualRent + " " + leaseEndMonth);
            } catch (IllegalArgumentException e) {
                errors.add("line " + i + ": " + e.getMessage())
            }
        }
        System.out.println(valid);
        System.out.println(errors);
    }
}