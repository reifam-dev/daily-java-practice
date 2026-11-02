import java.util.ArrayList;
import java.util.List;

/**
 * A bad row is reported with its real FILE line number (1-based,
 * counting the header) and the actual reason, then skipped - one bad
 * row never aborts the import. split(",", -1) keeps trailing empty
 * fields, so a missing last value is seen as blank, not as a shorter row.
 * NumberFormatException is a subclass of IllegalArgumentException, so one
 * catch block covers both bad numbers and the validation errors thrown here.
 */
public class Day195RentRollImport {

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
            int lineNumber = i + 1;
            String[] fields = LINES[i].split(",", -1);
            try {
                if (fields.length != 4) {
                    throw new IllegalArgumentException("expected 4 fields, got " + fields.length);
                }
                String tenant = fields[0].trim();
                if (tenant.isEmpty()) {
                    throw new IllegalArgumentException("tenant is blank");
                }
                double annualRent = Double.parseDouble(fields[2]);
                if (annualRent <= 0) {
                    throw new IllegalArgumentException("annual_rent must be positive");
                }
                int leaseEndMonth = Integer.parseInt(fields[3]);
                valid.add(tenant + " " + annualRent + " " + leaseEndMonth);
            } catch (IllegalArgumentException e) {
                errors.add("line " + lineNumber + ": " + e.getMessage());
            }
        }
        System.out.println(valid);
        for (String message : errors) {
            System.out.println(message);
        }
    }
}