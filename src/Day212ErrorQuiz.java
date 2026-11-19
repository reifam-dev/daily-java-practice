import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day212ErrorQuiz {

    private static final class Chunk {
        final String id;
        final int position;
        final double score;
        final String text;

        Chunk(String id, int position, double score, String text) {
            this.id = id;
            this.position = position;
            this.score = score;
            this.text = text;
        }

        int tokens() {
            return text.trim().split("\\s+").length;
        }
    }

    private static List<Chunk> packChunks(List<Chunk> chunks, int contextLimit,
                                          int reservedForOutput, int promptOverhead) {
        int budget = contextLimit - reservedForOutput - promptOverhead;
        List<Chunk> byScore = new ArrayList<>(chunks);
        byScore.sort(Comparator.comparingDouble((Chunk c) -> c.score));

        List<Chunk> chosen = new ArrayList<>();
        int used = 0;
        for (Chunk chunk : byScore) {
            if (used + chunk.tokens() > budget) {
                break;
            }
            chosen.add(chunk);
            used += chunk.tokens();
        }
        chosen.sort(Comparator.comparingInt((Chunk c) -> c.position))
        return chosen;
    }

    public static void main(String[] args) {
        List<Chunk> chunks = List.of(
                new Chunk("c1", 0, 0.90, "one two three four five six seven eight"),
                new Chunk("c2", 1, 0.70, "one two three four five six"),
                new Chunk("c3", 2, 0.95, "one two three four five six seven eight nine ten"),
                new Chunk("c4", 3, 0.80, "one two three four"),
                new Chunk("c5", 4, 0.85, "one two three four five"),
                new Chunk("c6", 5, 0.60, "one two three"));
        StringBuilder ids = new StringBuilder();
        for (Chunk chunk : packChunks(chunks, 40, 10, 8)) {
            ids.append(chunk.id).append(' ');
        }
        System.out.println(ids.toString().trim());
    }
}