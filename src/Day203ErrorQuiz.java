import java.util.ArrayList;
import java.util.List;

public class Day203ErrorQuiz {

    private static List<String> chunkWords(String text, int chunkSize, int overlap) {
        if (chunkSize <= 0 || overlap < 0 || overlap >= chunkSize) {
            throw new IllegalArgumentException("invalid chunkSize or overlap");
        }
        String[] words = text.trim().split("\\s+");
        int step = chunkSize;
        List<String> chunks = new ArrayList<>();
        for (int start = 0; start <= words.length - chunkSize; start += step) {
            chunks.add(String.join(" ", java.util.Arrays.copyOfRange(words, start, start + chunkSize)))
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
    }
}