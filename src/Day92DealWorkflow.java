import java.util.Objects;

final class Deal {
    private final String name;
    private final double marketValue;

    public Deal(String name, double marketValue) {
        this.name = name;
        this.marketValue = marketValue;
    }

    public String getName() {
        return this.name;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    @Override
    public String toString() {
        return "Deal{name='" + this.name + "', marketValue=" + this.marketValue + '}';
    }
}

public class Day92DealWorkflow {

    private static final double RISK_ESCALATION_THRESHOLD = 30.0;

    private final Deal deal;
    private double riskScore;
    private String outcome;

    public Day92DealWorkflow(Deal deal) {
        this.deal = Objects.requireNonNull(deal, "deal must not be null");
        this.riskScore = 0.0;
        this.outcome = "";
    }

    public double getRiskScore() {
        return this.riskScore;
    }

    public String getOutcome() {
        return this.outcome;
    }

    private void assessRisk() {
        this.riskScore += this.deal.getMarketValue() / 1_000_000.0;
    }

    private String routeByRisk() {
        if (this.riskScore > RISK_ESCALATION_THRESHOLD) {
            return "escalate";
        }
        return "recommend";
    }

    public String run() {
        assessRisk();
        String nextNode = routeByRisk();
        if (nextNode.equals("escalate")) {
            this.outcome = this.deal.getName() + " escalated for senior review.";
        } else {
            this.outcome = this.deal.getName() + " recommended for approval.";
        }
        return this.outcome;
    }

    @Override
    public String toString() {
        return "Day92DealWorkflow{deal=" + this.deal + ", riskScore=" + this.riskScore
                + ", outcome='" + this.outcome + "'}";
    }

    public static void main(String[] args) {
        Deal deal = new Deal("Riverside JV", 42000000.0);
        Day92DealWorkflow workflow = new Day92DealWorkflow(deal);
        System.out.println(workflow.run());
    }
}