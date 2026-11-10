import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Splits text into chunks of chunkSize words. Consecutive chunks share
 * `overlap` words so a sentence cut at a boundary still appears whole in
 * one of them. The window advances by (chunkSize - overlap), the final
 * partial window is KEPT, a text shorter than one chunk still yields one
 * chunk, and impossible settings are rejected.
 */
public class Day203TextChunker {

    private static List<String> chunkWords(String text, int chunkSize, int overlap) {
        if (chunkSize <= 0) {
            throw new IllegalArgumentException("chunkSize must be positive");
        }
        if (overlap < 0 || overlap >= chunkSize) {
            throw new IllegalArgumentException("overlap must be at least 0 and less than chunkSize");
        }
        List<String> chunks = new ArrayList<>();
        if (text.isBlank()) {
            return chunks;
        }

        String[] words = text.trim().split("\\s+");
        int step = chunkSize - overlap;
        for (int start = 0; start < words.length; start += step) {
            int end = Math.min(start + chunkSize, words.length);
            chunks.add(String.join(" ", Arrays.copyOfRange(words, start, end)));
            if (start + chunkSize >= words.length) {
                break;
            }
        }
        return chunks;
    }

    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            text.append("w").append(i).append(i < 19 ? " " : "");
        }
        for (String chunk : chunkWords(text.toString(), 8, 2)) {
            System.out.println(chunk);
        }
        System.out.println(chunkWords("only five words in this", 8, 2));
        try {
            chunkWords(text.toString(), 8, 8);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}