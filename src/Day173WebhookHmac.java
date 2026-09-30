import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

/**
 * Uses a genuine keyed HMAC (javax.crypto.Mac) and a constant-time
 * comparison (MessageDigest.isEqual), since a plain string == or
 * .equals() comparison leaks timing information exploitable to guess
 * a signature byte by byte.
 */
public class Day173WebhookHmac {
    private static final String WEBHOOK_SECRET =
            System.getenv().getOrDefault("WEBHOOK_SECRET", "dev-secret-for-local-testing");

    private static String computeSignature(String payload)
            throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(WEBHOOK_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] hash = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static boolean verifyWebhook(String payload, String receivedSignature)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String expected = computeSignature(payload);
        return java.security.MessageDigest.isEqual(
                expected.getBytes(StandardCharsets.UTF_8),
                receivedSignature.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String payload = "{\"event\": \"deal_created\"}";
        String sig = computeSignature(payload);
        System.out.println(verifyWebhook(payload, sig));
        System.out.println(verifyWebhook(payload, "tampered-signature"));
    }
}