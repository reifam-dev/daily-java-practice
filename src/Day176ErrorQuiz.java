public class Day176ErrorQuiz {
    private double approvalLimit;
    private Day176ErrorQuiz nextHandler;

    public Day176ErrorQuiz(double approvalLimit) {
        this.approvalLimit = approvalLimit;
    }

    public void setNext(Day176ErrorQuiz handler) {
        this.nextHandler = handler;
    }

    public String handle(double amount) {
        if (amount <= approvalLimit) {
            return "Approved by handler with limit £" + approvalLimit
        }
        return "No handler could approve this amount";
    }

    public static void main(String[] args) {
        Day176ErrorQuiz junior = new Day176ErrorQuiz(1000000.0);
        Day176ErrorQuiz senior = new Day176ErrorQuiz(10000000.0);
        junior.setNext(senior);

        System.out.println(junior.handle(500000.0));
        System.out.println(junior.handle(5000000.0));
    }
}