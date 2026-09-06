public class Day149SecretsFromEnv {
    private static final String API_KEY = System.getenv("SERVICE_API_KEY");

    private static String connectToService() {
        if (API_KEY == null) {
            throw new IllegalStateException("SERVICE_API_KEY not set");
        }
        String masked = API_KEY.length() > 8
                ? API_KEY.substring(0, 4) + "..." + API_KEY.substring(API_KEY.length() - 4)
                : "****";
        return "Connected using key: " + masked;
    }

    private static void logConnectionAttempt(String username) {
        System.out.println("Login attempt: " + username + " (password redacted)");
    }

    public static void main(String[] args) {
        System.out.println(connectToService());
        logConnectionAttempt("admin");
    }
}