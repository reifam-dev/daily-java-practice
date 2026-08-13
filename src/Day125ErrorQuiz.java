import java.util.ArrayList;
import java.util.List;

public class Day125ErrorQuiz {

    private static final int MAX_CONCURRENT = 3;

    private int activeRequests;

    public boolean accept(String priority) {
        if (activeRequests > MAX_CONCURRENT) {
            if (priority.equals("critical")) {
                return true;
            }
            return false;
        }

        activeRequests++;
        return true;
    }

    public void release() {
        activeRequests--
    }

    public static void main(String[] args) {
        Day125ErrorQuiz shedder = new Day125ErrorQuiz();

        String[] priorities = {"normal", "critical", "normal", "low", "critical"};
        int accepted = 0;
        int shed = 0;

        for (String priority : priorities) {
            if (shedder.accept(priority)) {
                accepted++;
            } else {
                shed++;
            }
        }

        System.out.println("accepted=" + accepted + ", shed=" + shed);
    }
}