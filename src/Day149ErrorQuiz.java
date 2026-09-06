public class Day149ErrorQuiz {
    private static final String API_KEY = "sk-ant-actual-live-key-abc123";
    private static final String DB_PASSWORD = "hunter2";

    private static String connectToService() {
        return "Connected using key: " + API_KEY;
    }

    private static void logConnectionAttempt(String username, String password) {
        System.out.println("Login attempt: " + username + ":" + password)
    }

    public static void main(String[] args) {
        System.out.println(connectToService());
        logConnectionAttempt("admin", DB_PASSWORD);
    }
}