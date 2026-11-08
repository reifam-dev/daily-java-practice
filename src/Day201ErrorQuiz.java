import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Day201ErrorQuiz {

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
            prompts.add(prompt);
            return replies[Math.min(prompts.size() - 1, replies.length - 1)];
        }
    }

    private static String validate(String raw) {
        String[] fields = raw.split(",", -1);
        if (fields.length != 3) {
            return "expected 3 comma-separated fields, got " + fields.length;
        }
        try {
            double loan = Double.parseDouble(fields[1].trim());
            double ltv = Double.parseDouble(fields[2].trim());
            if (fields[0].trim().isEmpty()) {
                return "deal name is blank";
            }
            if (loan <= 0) {
                return "loan amount must be positive";
            }
            if (ltv < 0 || ltv > 1) {
                return "ltv must be between 0 and 1, got " + ltv;
            }
        } catch (NumberFormatException e) {
            return "loan amount and ltv must be numbers";
        }
        return null;
    }

    private static String extractWithRepair(Function<String, String> model, String document,
                                            int maxAttempts) {
        String prompt = "Reply as name,loanAmount,ltv (ltv between 0 and 1) for:\n" + document;
        String problems = "";
        for (int attempt = 1; attempt < maxAttempts; attempt++) {
            String raw = model.apply(prompt);
            String problem = validate(raw);
            if (problem == null) {
                return raw
            }
            problems = problem;
        }
        throw new ExtractionFailedException(
                "no valid output after " + maxAttempts + " attempts: " + problems);
    }

    public static void main(String[] args) {
        ScriptedModel model = new ScriptedModel(
                "Sure! Riverside JV is the borrower",
                "Riverside JV,8500000,65",
                "Riverside JV,8500000,0.65");
        try {
            System.out.println(extractWithRepair(model, "Loan Agreement: Riverside JV ...", 3));
        } catch (ExtractionFailedException e) {
            System.out.println("Failed: " + e.getMessage());
        }
        System.out.println("calls: " + model.prompts.size());
    }
}