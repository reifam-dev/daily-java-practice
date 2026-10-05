/**
 * Each handler either satisfies a request or explicitly passes it
 * along to the next handler in the chain, so callers don't need to
 * know which specific handler will ultimately deal with it.
 */
public class Day176ApprovalChain {
    private final double approvalLimit;
    private Day176ApprovalChain nextHandler;

    public Day176ApprovalChain(double approvalLimit) {
        this.approvalLimit = approvalLimit;
    }

    public Day176ApprovalChain setNext(Day176ApprovalChain handler) {
        this.nextHandler = handler;
        return handler;
    }

    public String handle(double amount) {
        if (amount <= this.approvalLimit) {
            return "Approved by handler with limit £" + this.approvalLimit;
        }

        if (this.nextHandler != null) {
            return this.nextHandler.handle(amount);
        }

        return "No handler could approve this amount";
    }

    public static void main(String[] args) {
        Day176ApprovalChain junior = new Day176ApprovalChain(1000000.0);
        Day176ApprovalChain senior = new Day176ApprovalChain(10000000.0);
        Day176ApprovalChain director = new Day176ApprovalChain(50000000.0);

        junior.setNext(senior);
        senior.setNext(director);

        System.out.println(junior.handle(500000.0));
        System.out.println(junior.handle(5000000.0));
        System.out.println(junior.handle(100000000.0));
    }
}