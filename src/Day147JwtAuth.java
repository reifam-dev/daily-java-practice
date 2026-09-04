import java.util.HashMap;
import java.util.Map;

final class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) { super(message); }
}

public class Day147JwtAuth {
    private static Map<String, Object> createToken(String userId, long expiresInMillis) {
        Map<String, Object> token = new HashMap<>();
        token.put("userId", userId);
        token.put("expiresAt", System.currentTimeMillis() + expiresInMillis);
        return token;
    }

    private static boolean verifyToken(Map<String, Object> token) {
        long expiresAt = (long) token.get("expiresAt");
        return expiresAt > System.currentTimeMillis();
    }

    private static String getUserId(Map<String, Object> token) {
        if (!verifyToken(token)) {
            throw new InvalidTokenException("Invalid or expired token");
        }
        return (String) token.get("userId");
    }

    public static void main(String[] args) {
        Map<String, Object> token = createToken("user-1", 10000);
        System.out.println(getUserId(token));
    }
}