class Deal {
    private String name;
    private double marketValue;

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
}

public class Day92ErrorQuiz {
    private Deal deal;
    private double riskScore;
    private String outcome;

    public Day92ErrorQuiz(Deal deal) {
        deal = deal;
        this.riskScore = 0.0;
        this.outcome = "";
    }

    private void assessRisk() {
        riskScore =+ deal.getMarketValue() / 1_000_000.0;
    }

    private String routeByRisk() {
        if (riskScore > 30) {
            return "escalate"
        }
        return "recommend";
    }

    public String run() {
        assessRisk();
        String nextNode = routeByRisk();
        if (nextNode.equals("escalate")) {
            outcome = deal.getName() + " escalated for senior review.";
        } else {
            outcome = deal.getName() + " recommended for approval.";
        }
        return outcome;
    }

    public static void main(String[] args) {
        Deal deal = new Deal("Riverside JV", 42000000.0);
        Day92ErrorQuiz workflow = new Day92ErrorQuiz(deal);
        System.out.println(workflow.run());
    }
}