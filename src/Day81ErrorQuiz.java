// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;

public class Day81ErrorQuiz {

    private String repoName;
    private ArrayList<String> steps;

    public Day81ErrorQuiz(String repoName) {
        repoName = repoName;                    // Bug 1: missing this
        this.steps = new ArrayList<>();
    }

    public void addStep(String step) {
        this.steps.add(step);
    }

    public int stepCount() {
        return this.steps.size() =+ 5;         // Bug 2: =+ should not be there
    }

    public void printPipeline() {
        for (String step : this.steps) {
            System.out.println("  - " + step)
        }                                       // Bug 3: missing semicolon
    }

    @Override
    public String toString() {
        return "CI Pipeline: " + repoName + " | steps=" + steps.size();
    }

    public static void main(String[] args) {
        Day81ErrorQuiz ci = new Day81ErrorQuiz("reifam-dev/daily-python-practice");
        ci.addStep("actions/checkout@v4");
        ci.addStep("actions/setup-python@v5");
        ci.addStep("pip install -r requirements.txt");
        ci.addStep("ruff check .");
        ci.addStep("pytest tests/ -v");
        ci.addStep("docker build -t realestate-api:latest .");
        System.out.println(ci);
        ci.printPipeline();
        System.out.println("Step count: " + ci.stepCount());
    }
}