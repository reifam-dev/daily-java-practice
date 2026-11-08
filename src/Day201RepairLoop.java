import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Model output is untrusted text: validate it, and on failure send the
 * model the SPECIFIC problem and ask again - up to a fixed number of
 * attempts, then fail loudly rather than returning something half-valid.
 * A scripted fake model keeps this offline and deterministic. (This Java
 * version uses a simple name,loanAmount,ltv line instead of JSON, to stay
 * dependency-free.)
 */
public class Day201RepairLoop {

    private static final class ExtractionFailedException extends RuntimeException {
        ExtractionFailedException(String message) {
            super(message);
        }
    }

    private static final class ScriptedModel implements Function<String, String> {
        private final String[] replies;
        final List<String> prompts = new ArrayList<>();

        ScriptedModel(String... replies) {
            this.replies = replies;
        }

        @Override
        public String apply(String prompt) {
            this.prompts.add(prompt);
            return this.replies[Math.min(this.prompts.size() - 1, this.replies.length - 1)];
        }
    }

    /** Returns null if the reply is valid, otherwise a description of the problem. */
    private static String validate(String raw) {
        String[] fields = raw.split(",", -1);
        if (fields.length != 3) {
            return "expected 3 comma-separated fields, got " + fields.length;
        }
        if (fields[0].trim().isEmpty()) {
            return "deal name is blank";
        }
        double loan;
        double ltv;
        try {
            loan = Double.parseDouble(fields[1].trim());
            ltv = Double.parseDouble(fields[2].trim());
        } catch (NumberFormatException e) {
            return "loan amount and ltv must be numbers";
        }
        if (loan <= 0) {
            return "loan amount must be positive";
        }
        if (ltv < 0 || ltv > 1) {
            return "ltv must be between 0 and 1, got " + ltv;
        }
        return null;
    }

    private static String extractWithRepair(Function<String, String> model, String document,
                                            int maxAttempts) {
        String basePrompt = "Reply as name,loanAmount,ltv (ltv between 0 and 1) for:\n" + document;
        String prompt = basePrompt;
        String problems = "";

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            String raw = model.apply(prompt);
            String problem = validate(raw);
            if (problem == null) {
                return raw;
            }
            problems = problem;
            prompt = basePrompt + "\n\nYour previous answer was invalid: " + problems
                    + "\nReturn corrected output only.";
        }
        throw new ExtractionFailedException(
                "no valid output after " + maxAttempts + " attempts: " + problems);
    }

    public static void main(String[] args) {
        ScriptedModel model = new ScriptedModel(
                "Sure! Riverside JV is the borrower",
                "Riverside JV,8500000,65",
                "Riverside JV,8500000,0.65");
        System.out.println(extractWithRepair(model, "Loan Agreement: Riverside JV ...", 3));
        System.out.println("calls: " + model.prompts.size());
        System.out.println("feedback sent: " + !model.prompts.get(0).equals(model.prompts.get(1)));

        ScriptedModel stubborn = new ScriptedModel(",,");
        try {
            extractWithRepair(stubborn, "Loan Agreement: Riverside JV ...", 3);
        } catch (ExtractionFailedException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}