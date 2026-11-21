import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day214ErrorQuiz {

    private static final Pattern API_KEY = Pattern.compile("sk-[A-Za-z0-9_-]{8,}");
    private static final Pattern BEARER = Pattern.compile("Bearer .*");
    private static final Pattern EMAIL = Pattern.compile("[\\w.+-]+@[\\w-]+(?:\\.[\\w-]+)+");

    private static String redact(String text) {
        text = API_KEY.matcher(text).replaceAll("[API_KEY REDACTED]");
        text = BEARER.matcher(text).replaceAll("[BEARER REDACTED]");
        text = EMAIL.matcher(text).replaceAll("[EMAIL REDACTED]")
        return text;
    }

    private static void safeLog(String message, List<String> sink) {
        sink.add(redact(message));
    }

    public static void main(String[] args) {
        List<String> sink = new ArrayList<>();
        safeLog("Authorization: Bearer abc.def.ghi; invoice total 8,500,000", sink);
        safeLog("retry with header bearer zzz.yyy.xxx", sink);
        safeLog("payer jane.doe@example.com used key sk-live_ABCD1234efgh5678", sink);
        for (String line : sink) {
            System.out.println(line);
        }
    }
}