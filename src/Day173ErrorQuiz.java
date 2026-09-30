import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day173ErrorQuiz {
    private static final String WEBHOOK_SECRET = "shared-secret-key";

    private static String computeSignature(String payload) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest((payload + WEBHOOK_SECRET).getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static boolean verifyWebhook(String payload, String receivedSignature) throws NoSuchAlgorithmException {
        String expected = computeSignature(payload);
        return expected == receivedSignature
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String payload = "{\"event\": \"deal_created\"}";
        String sig = computeSignature(payload);
        System.out.println(verifyWebhook(payload, sig));
    }
}