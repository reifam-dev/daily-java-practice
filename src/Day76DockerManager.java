import java.util.ArrayList;

/**
 * Day 76 – Docker basics in Java: Dockerfile and CLI command generation.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day76DockerManager {

    private String imageName;
    private String pythonVersion;
    private int port;
    private String entrypoint;
    private ArrayList<String> envVars;

    public Day76DockerManager(String imageName, int port) {
        this.imageName = imageName;
        this.port = port;
        this.pythonVersion = "3.12";
        this.entrypoint = "main.py";
        this.envVars = new ArrayList<>();
    }

    public String getImageName() { return this.imageName; }
    public int getPort() { return this.port; }

    public void addEnvVar(String key, String value) {
        this.envVars.add("ENV " + key + "=" + value);
    }

    public String generateDockerfile() {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM python:").append(this.pythonVersion).append("-slim\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY requirements.txt .\n");
        sb.append("RUN pip install --no-cache-dir -r requirements.txt\n");
        sb.append("COPY . .\n");
        sb.append("EXPOSE ").append(this.port).append("\n");
        for (String env : this.envVars) {
            sb.append(env).append("\n");
        }
        sb.append("CMD [\"python\", \"").append(this.entrypoint).append("\"]");
        return sb.toString();
    }

    public String buildCommand(String tag) {
        return "docker build -t " + this.imageName + ":" + tag + " .";
    }

    public String runCommand(boolean detached) {
        String flag = detached ? "-d " : "";
        return "docker run " + flag + "-p " + this.port + ":" + this.port + " " + this.imageName;
    }

    @Override
    public String toString() {
        return String.format(
                "DockerManager | image=%s | port=%d | envVars=%d",
                this.imageName, this.port, this.envVars.size()
        );
    }

    public static void main(String[] args) {
        Day76DockerManager dm = new Day76DockerManager("realestate-api", 8000);
        dm.addEnvVar("ENV", "production");
        dm.addEnvVar("LOG_LEVEL", "INFO");

        System.out.println("=== Dockerfile ===");
        System.out.println(dm.generateDockerfile());
        System.out.println("\n" + dm.buildCommand("v1.0"));
        System.out.println(dm.runCommand(true));
        System.out.println(dm);
    }
}