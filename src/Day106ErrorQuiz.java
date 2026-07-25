import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Day106ErrorQuiz {
    private Map<String, String> cache;

    public Day106ErrorQuiz() {
        cache = new HashMap<>();
    }

    private String cacheKey(String question) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(question.toLowerCase().trim().getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : hash) {
            builder.append(String.format("%02x", b))
        }
        return builder.toString();
    }

    public String askWithCache(String question) throws NoSuchAlgorithmException {
        String key = cacheKey(question);

        if (cache.containsKey(key)) {
            System.out.println("cache hit");
            return cache.get(key);
        }

        System.out.println("cache miss");
        String answer = "Typical logistics LTV covenant sits around 60-65%.";
        cache.put(key, answer)
        return answer;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Day106ErrorQuiz qa = new Day106ErrorQuiz();
        System.out.println(qa.askWithCache("What is a typical LTV covenant for logistics assets?"));
        System.out.println(qa.askWithCache("What is a typical LTV covenant for logistics assets?"));
    }
}