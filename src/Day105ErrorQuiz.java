import java.util.ArrayList;
import java.util.List;

class StreamChunk {
    private String text;
    private int sequenceNumber;

    public StreamChunk(String text, int sequenceNumber) {
        text = text;
        this.sequenceNumber = sequenceNumber;
    }

    public String getText() {
        return text;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }
}

public class Day105ErrorQuiz {

    private static List<StreamChunk> simulateStream(String[] words) {
        List<StreamChunk> chunks = new ArrayList<>();
        int sequence = 0;
        for (String word : words) {
            chunks.add(new StreamChunk(word + " ", sequence))
            sequence += 1;
        }
        return chunks;
    }

    private static String accumulate(List<StreamChunk> chunks) {
        StringBuilder builder = new StringBuilder();
        for (StreamChunk chunk : chunks) {
            builder.append(chunk.getText());
        }
        return builder.toString()
    }

    public static void main(String[] args) {
        String[] words = {"Riverside", "JV", "is", "a", "logistics", "deal"};
        List<StreamChunk> chunks = simulateStream(words);
        System.out.println(accumulate(chunks));
        System.out.println("Chunks received: " + chunks.size());
    }
}