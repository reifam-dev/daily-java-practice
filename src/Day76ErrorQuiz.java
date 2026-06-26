// This file contains 3 deliberate bugs. Find and fix them.
public class Day76ErrorQuiz {

    private String imageName;
    private int port;

    public Day76ErrorQuiz(String imageName, int port) {
        imageName = imageName;                  // Bug 1: missing this
        this.port = port;
    }

    public String buildCommand(String tag) {
        return "docker build -t " + this.imageName + ":" + tag + " .";
    }

    public String runCommand() {
        return "docker run -p " + this.port + ":" + this.port =+ " " + this.imageName;  // Bug 2: =+ should be +
    }

    public String generateDockerfile() {
        return "FROM python:3.12-slim\n" +
                "WORKDIR /app\n" +
                "COPY . .\n" +
                "EXPOSE " + this.port + "\n" +
                "CMD [\"python\", \"main.py\"]"
    }                                           // Bug 3: missing semicolon

    @Override
    public String toString() {
        return "DockerManager: " + imageName + " | port=" + port;
    }

    public static void main(String[] args) {
        Day76ErrorQuiz dm = new Day76ErrorQuiz("realestate-api", 8000);
        System.out.println(dm.buildCommand("latest"));
        System.out.println(dm.runCommand());
        System.out.println(dm.generateDockerfile());
    }
}