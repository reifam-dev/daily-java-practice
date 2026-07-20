class DealPromptBuilder {
    private String systemPrompt;
    private String fewShotExample;

    public DealPromptBuilder(String systemPrompt, String fewShotExample) {
        systemPrompt = systemPrompt;
        this.fewShotExample = fewShotExample;
    }

    public String buildPrompt(String dealSummary, String question) {
        return fewShotExample + "Deal: " + dealSummary + "\nQuestion: " + question + "\nAnswer:"
    }

    public String getSystemPrompt() {
        return systemPrompt;
    }
}

public class Day101ErrorQuiz {
    private static final String FEW_SHOT_EXAMPLE =
            "Example:\nDeal: LTV 55%, yield 6.1%.\n"
                    + "Question: Is this deal within a 65% LTV covenant?\n"
                    + "Answer: Yes, 55% is within the 65% covenant.\n\n";

    private static boolean checkLtvCovenant(double ltv, double covenant) {
        if (ltv = covenant) {
            return true;
        }
        return ltv < covenant;
    }

    public static void main(String[] args) {
        String systemPrompt = "You are a real estate investment analyst. "
                + "Answer only using the deal information provided.";
        DealPromptBuilder builder = new DealPromptBuilder(systemPrompt, FEW_SHOT_EXAMPLE);
        String prompt = builder.buildPrompt(
                "LTV 72%, yield 5.4%, sector: logistics.",
                "Is this deal within a 65% LTV covenant?"
        );
        System.out.println(prompt);
        System.out.println(checkLtvCovenant(0.72, 0.65));
    }
}