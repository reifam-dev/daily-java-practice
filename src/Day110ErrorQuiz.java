import java.util.ArrayList;
import java.util.List;

class TestCase {
    private String question;
    private String expectedContains;

    public TestCase(String question, String expectedContains) {
        question = question;
        this.expectedContains = expectedContains;
    }

    public String getQuestion() {
        return question;
    }

    public String getExpectedContains() {
        return expectedContains;
    }
}

public class Day110ErrorQuiz {

    private static String generateAnswer(String question) {
        if (question.contains("60%") && question.contains("10m")) {
            return "60% of 10m is 6m.";
        }
        return "65% of 20m is 13m.";
    }

    private static boolean judgeAnswer(String answer, String expectedContains) {
        return answer.contains(expectedContains)
    }

    private static double runEvalSuite(List<TestCase> testCases) {
        int passed = 0;
        for (TestCase testCase : testCases) {
            String answer = generateAnswer(testCase.getQuestion());
            if (judgeAnswer(answer, testCase.getExpectedContains())) {
                passed = passed + 1;
            }
        }
        return passed / testCases.size();
    }

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("What is 60% LTV of a 10m property?", "6m"));
        testCases.add(new TestCase("What is 65% LTV of a 20m property?", "13m"));

        double score = runEvalSuite(testCases);
        System.out.println("Pass rate: " + score);
    }
}