import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Simulates a structured-logging LLM call wrapper, recording question,
 * answer, latency, and token count as a single log record - a Java
 * analogue of the Python logging.getLogger + JSON pattern.
 */
public class Day111ObservableDealAgent {

    private static Map<String, Object> buildLogRecord(
            String question, String answer, long durationMillis, int tokens) {
        Map<String, Object> record = new LinkedHashMap<>();
        record.put("question", question);
        record.put("answer", answer);
        record.put("durationMillis", durationMillis);
        record.put("tokens", tokens);
        return record;
    }

    private static String simulateLlmCall(String question) throws InterruptedException {
        Thread.sleep(50);
        return "Typical logistics LTV covenant sits around 60-65%.";
    }

    public static String askWithObservability(String question) throws InterruptedException {
        long start = System.currentTimeMillis();
        String answer = simulateLlmCall(question);
        long duration = System.currentTimeMillis() - start;

        Map<String, Object> record = buildLogRecord(question, answer, duration, 42);
        System.out.println(record);

        return answer;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(askWithObservability("What is a typical LTV covenant for logistics assets?"));
    }
}