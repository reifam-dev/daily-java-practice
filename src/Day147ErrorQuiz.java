import java.util.HashMap;
import java.util.Map;

public class Day147ErrorQuiz {
    private static final String SECRET = "super-secret-key";

    private static Map<String, Object> createToken(String userId, long expiresInMillis) {
        Map<String, Object> token = new HashMap<>();
        token.put("userId", userId);
        token.put("expiresAt", System.currentTimeMillis() + expiresInMillis);
        token.put("secret", SECRET);
        return token;
    }

    private static boolean verifyToken(Map<String, Object> token) {
        long expiresAt = (long) token.get("expiresAt");
        return expiresAt < System.currentTimeMillis()
    }

    public static void main(String[] args) {
        Map<String, Object> token = createToken("user-1", 10000);
        System.out.println(verifyToken(token));
    }
}