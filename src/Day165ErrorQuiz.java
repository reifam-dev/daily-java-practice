interface Visitor {
    void visit(Deal deal);
}

class Deal {
    double marketValue;

    Deal(double marketValue) {
        this.marketValue = marketValue;
    }

    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class LoanFacility {
    double principal;

    LoanFacility(double principal) {
        this.principal = principal;
    }

    void accept(Visitor visitor) {
        visitor.visit(this)
    }
}

class RiskReportVisitor implements Visitor {
    public void visit(Deal deal) {
        System.out.println("Deal risk: marketValue=" + deal.marketValue);
    }
}

public class Day165ErrorQuiz {
    public static void main(String[] args) {
        Deal deal = new Deal(12500000.0);
        RiskReportVisitor visitor = new RiskReportVisitor();
        deal.accept(visitor);
    }
}