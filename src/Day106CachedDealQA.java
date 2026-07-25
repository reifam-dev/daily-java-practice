import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory exact-match cache for question/answer pairs, keyed by a
 * SHA-256 hash of the normalised question - a Java analogue of the
 * Python hashlib-based cache.
 */
public class Day106CachedDealQa {

    private final Map<String, String> cache;

    public Day106CachedDealQa() {
        this.cache = new HashMap<>();
    }

    private String cacheKey(String question) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(question.toLowerCase().trim().getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : hash) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public String askWithCache(String question) throws NoSuchAlgorithmException {
        String key = cacheKey(question);

        if (this.cache.containsKey(key)) {
            System.out.println("cache hit");
            return this.cache.get(key);
        }

        System.out.println("cache miss");
        String answer = "Typical logistics LTV covenant sits around 60-65%.";
        this.cache.put(key, answer);
        return answer;
    }

    public int cacheSize() {
        return this.cache.size();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Day106CachedDealQa qa = new Day106CachedDealQa();
        System.out.println(qa.askWithCache("What is a typical LTV covenant for logistics assets?"));
        System.out.println(qa.askWithCache("What is a typical LTV covenant for logistics assets?"));
        System.out.println("Cache entries: " + qa.cacheSize());
    }
}