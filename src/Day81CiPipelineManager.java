import java.util.ArrayList;

/**
 * Day 81 – GitHub Actions CI/CD concepts in Java: workflow step management.
 * Pipeline includes: pytest, ruff linting, and Docker image build.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day81CiPipelineManager {

    private String repoName;
    private String pythonVersion;
    private ArrayList<String[]> steps;
    private ArrayList<String> branches;

    public Day81CiPipelineManager(String repoName, String pythonVersion) {
        this.repoName = repoName;
        this.pythonVersion = pythonVersion;
        this.steps = new ArrayList<>();
        this.branches = new ArrayList<>();
        this.branches.add("main");
        initialiseDefaultSteps();
    }

    public String getRepoName() { return this.repoName; }
    public int getStepCount() { return this.steps.size(); }

    private void initialiseDefaultSteps() {
        this.steps.add(new String[]{"Checkout", "actions/checkout@v4"});
        this.steps.add(new String[]{"Setup Python", "actions/setup-python@v5 | " + this.pythonVersion});
        this.steps.add(new String[]{"Install deps", "pip install -r requirements.txt"});
        this.steps.add(new String[]{"Lint", "ruff check ."});
        this.steps.add(new String[]{"Run tests", "pytest tests/ -v --tb=short"});
        this.steps.add(new String[]{"Build Docker", "docker build -t realestate-api:latest ."});
    }

    public void addStep(String name, String command) {
        this.steps.add(new String[]{name, command});
    }

    public void addBranch(String branch) {
        this.branches.add(branch);
    }

    public boolean validateStep(String[] step) {
        return step != null && step.length == 2 && !step[0].isEmpty() && !step[1].isEmpty();
    }

    public String generateBadge() {
        return "![CI](https://github.com/" + this.repoName + "/actions/workflows/ci.yml/badge.svg)";
    }

    public void printWorkflow() {
        System.out.println("name: CI");
        System.out.println("on:");
        System.out.println("  push:");
        System.out.println("    branches: " + this.branches);
        System.out.println("jobs:");
        System.out.println("  test:");
        System.out.println("    runs-on: ubuntu-latest");
        System.out.println("    steps:");
        for (String[] step : this.steps) {
            System.out.printf("      - name: %s%n        run: %s%n", step[0], step[1]);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "CIPipelineManager | repo=%s | python=%s | steps=%d | branches=%s",
                this.repoName, this.pythonVersion, this.steps.size(), this.branches
        );
    }

    public static void main(String[] args) {
        Day81CiPipelineManager ci =
                new Day81CiPipelineManager("reifam-dev/daily-python-practice", "3.12");
        ci.addBranch("dev");

        ci.printWorkflow();
        System.out.println();
        System.out.println("Badge: " + ci.generateBadge());
        System.out.println(ci);
    }
}