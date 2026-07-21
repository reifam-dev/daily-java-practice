final class ReviewState {
    private final String dealSummary;
    private String financialReview;
    private String legalReview;
    private String finalVerdict;

    public ReviewState(String dealSummary) {
        this.dealSummary = dealSummary;
        this.financialReview = "";
        this.legalReview = "";
        this.finalVerdict = "";
    }

    public String getDealSummary() {
        return this.dealSummary;
    }

    public void setFinancialReview(String review) {
        this.financialReview = review;
    }

    public void setLegalReview(String review) {
        this.legalReview = review;
    }

    public String getFinancialReview() {
        return this.financialReview;
    }

    public String getLegalReview() {
        return this.legalReview;
    }

    public void setFinalVerdict(String verdict) {
        this.finalVerdict = verdict;
    }

    public String getFinalVerdict() {
        return this.finalVerdict;
    }
}

/**
 * Java analogue of the Python parallel-review workflow: two independent
 * reviewer steps populate separate fields on shared state, then a
 * combining step reads both once both have run.
 */
public class Day102MultiAgentReview {

    private static void financialReviewer(ReviewState state) {
        state.setFinancialReview("Financial: yield supports valuation.");
    }

    private static void legalReviewer(ReviewState state) {
        state.setLegalReview("Legal: title and covenants clear.");
    }

    private static void combineVerdict(ReviewState state) {
        String verdict = "Financial: " + state.getFinancialReview()
                + "\nLegal: " + state.getLegalReview();
        state.setFinalVerdict(verdict);
    }

    public static void main(String[] args) {
        ReviewState state = new ReviewState("Logistics portfolio, 6.2% yield, 65% LTV.");
        financialReviewer(state);
        legalReviewer(state);
        combineVerdict(state);
        System.out.println(state.getFinalVerdict());
    }
}