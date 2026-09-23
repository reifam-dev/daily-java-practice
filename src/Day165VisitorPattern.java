interface Visitor {
    void visitDeal(Deal deal);
    void visitLoanFacility(LoanFacility facility);
}

final class Deal {
    final double marketValue;

    Deal(double marketValue) {
        this.marketValue = marketValue;
    }

    void accept(Visitor visitor) {
        visitor.visitDeal(this);
    }
}

final class LoanFacility {
    final double principal;
    final double rate;

    LoanFacility(double principal, double rate) {
        this.principal = principal;
        this.rate = rate;
    }

    void accept(Visitor visitor) {
        visitor.visitLoanFacility(this);
    }
}

/**
 * Separates the risk-reporting operation from the Deal/LoanFacility
 * classes it operates on, with each accept() dispatching to a
 * type-specific visit method.
 */
final class RiskReportVisitor implements Visitor {
    @Override
    public void visitDeal(Deal deal) {
        System.out.println("Deal risk: marketValue=" + deal.marketValue);
    }

    @Override
    public void visitLoanFacility(LoanFacility facility) {
        System.out.printf("Loan risk: principal=%.2f, rate=%.2f%%%n",
                facility.principal, facility.rate * 100);
    }
}

public class Day165VisitorPattern {
    public static void main(String[] args) {
        Deal deal = new Deal(12500000.0);
        LoanFacility facility = new LoanFacility(8000000.0, 0.0525);
        RiskReportVisitor visitor = new RiskReportVisitor();

        deal.accept(visitor);
        facility.accept(visitor);
    }
}