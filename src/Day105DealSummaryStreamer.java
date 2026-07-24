import java.util.ArrayList;
import java.util.List;

final class StreamChunk {
    private final String text;
    private final int sequenceNumber;

    public StreamChunk(String text, int sequenceNumber) {
        this.text = text;
        this.sequenceNumber = sequenceNumber;
    }

    public String getText() {
        return this.text;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    @Override
    public String toString() {
        return "StreamChunk{seq=" + this.sequenceNumber + ", text='" + this.text + "'}";
    }
}

/**
 * Simulates a streamed response as a sequence of chunks, mirroring
 * the Python Anthropic streaming client's text_stream accumulation
 * pattern in a language without native async generators used here.
 */
public class Day105DealSummaryStreamer {

    private static List<StreamChunk> simulateStream(String[] words) {
        List<StreamChunk> chunks = new ArrayList<>();
        int sequence = 0;
        for (String word : words) {
            chunks.add(new StreamChunk(word + " ", sequence));
            sequence += 1;
        }
        return chunks;
    }

    private static String accumulate(List<StreamChunk> chunks) {
        StringBuilder builder = new StringBuilder();
        for (StreamChunk chunk : chunks) {
            builder.append(chunk.getText());
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String[] words = {"Riverside", "JV", "is", "a", "logistics", "deal"};
        List<StreamChunk> chunks = simulateStream(words);
        System.out.println(accumulate(chunks));
        System.out.println("Chunks received: " + chunks.size());
    }
}