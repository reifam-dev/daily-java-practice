import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Anything that goes to a log (or to a model) is scrubbed first. Patterns
 * match a TOKEN, not the rest of the line (a greedy ".*" would also delete
 * the useful text after it), they ignore case (headers arrive as Bearer,
 * bearer or BEARER), and the method that logs writes the REDACTED text.
 * Pattern matching reduces leaks; it cannot catch every format.
 */
public class Day214PiiRedaction {

    private static final Pattern API_KEY = Pattern.compile("sk-[A-Za-z0-9_-]{8,}");
    private static final Pattern BEARER =
            Pattern.compile("bearer\\s+[A-Za-z0-9._~+/-]+=*", Pattern.CASE_INSENSITIVE);
    private static final Pattern EMAIL = Pattern.compile("[\\w.+-]+@[\\w-]+(?:\\.[\\w-]+)+");
    private static final Pattern IBAN = Pattern.compile("\\b[A-Z]{2}\\d{2}[A-Z0-9]{11,30}\\b");

    private static String redact(String text) {
        text = API_KEY.matcher(text).replaceAll("[API_KEY REDACTED]");
        text = BEARER.matcher(text).replaceAll("[BEARER REDACTED]");
        text = EMAIL.matcher(text).replaceAll("[EMAIL REDACTED]");
        text = IBAN.matcher(text).replaceAll("[IBAN REDACTED]");
        return text;
    }

    private static void safeLog(String message, List<String> sink) {
        sink.add(redact(message));
    }

    public static void main(String[] args) {
        List<String> sink = new ArrayList<>();
        safeLog("Authorization: Bearer abc.def.ghi; invoice total 8,500,000", sink);
        safeLog("retry with header bearer zzz.yyy.xxx", sink);
        safeLog("payer jane.doe@example.com paid GB29NWBK60161331926819 using key sk-live_ABCD1234efgh5678", sink);
        for (String line : sink) {
            System.out.println(line);
        }
    }
}