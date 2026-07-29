import java.util.ArrayList;
import java.util.List;

final class TestCase {
    private final String question;
    private final String expectedContains;

    public TestCase(String question, String expectedContains) {
        this.question = question;
        this.expectedContains = expectedContains;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getExpectedContains() {
        return this.expectedContains;
    }
}

/**
 * Simulated evaluation harness: generates an answer per test case and
 * judges it against an expected substring, reporting an overall pass
 * rate - a Java analogue of the Python LLM-as-judge pattern.
 */
public class Day110DealAnswerEvaluator {

    private static String generateAnswer(String question) {
        if (question.contains("60%") && question.contains("10m")) {
            return "60% of 10m is 6m.";
        }
        return "65% of 20m is 13m.";
    }

    private static boolean judgeAnswer(String answer, String expectedContains) {
        return answer.contains(expectedContains);
    }

    private static double runEvalSuite(List<TestCase> testCases) {
        if (testCases.isEmpty()) {
            return 0.0;
        }

        int passed = 0;
        for (TestCase testCase : testCases) {
            String answer = generateAnswer(testCase.getQuestion());
            if (judgeAnswer(answer, testCase.getExpectedContains())) {
                passed = passed + 1;
            }
        }
        return (double) passed / testCases.size();
    }

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("What is 60% LTV of a 10m property?", "6m"));
        testCases.add(new TestCase("What is 65% LTV of a 20m property?", "13m"));

        double score = runEvalSuite(testCases);
        System.out.printf("Pass rate: %.0f%%%n", score * 100);
    }
}