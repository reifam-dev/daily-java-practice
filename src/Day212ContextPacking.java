import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A prompt has a fixed token budget: the context limit MINUS the tokens
 * reserved for the answer MINUS the fixed prompt overhead. Chunks are
 * taken most-relevant first; one that does not fit is SKIPPED (a smaller,
 * lower-ranked chunk may still fit), and the chosen chunks are put back in
 * DOCUMENT order. Tokens are counted as words here; a real system uses the
 * model's own tokenizer.
 */
public class Day212ContextPacking {

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
            return this.text.trim().split("\\s+").length;
        }
    }

    private static List<Chunk> packChunks(List<Chunk> chunks, int contextLimit,
                                          int reservedForOutput, int promptOverhead) {
        int budget = contextLimit - reservedForOutput - promptOverhead;
        if (budget <= 0) {
            throw new IllegalArgumentException("no token budget left for any context");
        }

        List<Chunk> byScore = new ArrayList<>(chunks);
        byScore.sort(Comparator.comparingDouble((Chunk c) -> c.score).reversed());

        List<Chunk> chosen = new ArrayList<>();
        int used = 0;
        for (Chunk chunk : byScore) {
            if (used + chunk.tokens() > budget) {
                continue;
            }
            chosen.add(chunk);
            used += chunk.tokens();
        }
        chosen.sort(Comparator.comparingInt((Chunk c) -> c.position));
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
        int used = 0;
        for (Chunk chunk : packChunks(chunks, 40, 10, 8)) {
            ids.append(chunk.id).append(' ');
            used += chunk.tokens();
        }
        System.out.println(ids.toString().trim() + " | tokens " + used);
    }
}