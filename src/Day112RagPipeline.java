import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

final class Document {
    private final String text;
    private final int id;

    public Document(String text, int id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "[" + this.id + "] " + this.text;
    }
}

/**
 * Java analogue of the Python RAG pipeline's reranking step: scores
 * documents by keyword overlap with the question and returns them
 * ordered from most to least relevant.
 */
public class Day112RagPipeline {

    private final List<Document> documents;

    public Day112RagPipeline() {
        this.documents = new ArrayList<>();
    }

    public void indexDocuments(List<String> texts) {
        for (int i = 0; i < texts.size(); i++) {
            this.documents.add(new Document(texts.get(i), i));
        }
    }

    private int overlapScore(String question, String doc) {
        String[] questionWords = question.toLowerCase().split(" ");
        String[] docWords = doc.toLowerCase().split(" ");
        int score = 0;
        for (String qw : questionWords) {
            for (String dw : docWords) {
                if (qw.equals(dw)) {
                    score++;
                }
            }
        }
        return score;
    }

    public List<Document> rerank(String question) {
        List<Document> ranked = new ArrayList<>(this.documents);
        ranked.sort(Comparator.comparingInt(
                (Document d) -> overlapScore(question, d.getText())).reversed());
        return ranked;
    }

    public static void main(String[] args) {
        Day112RagPipeline pipeline = new Day112RagPipeline();
        pipeline.indexDocuments(List.of(
                "Loan Agreement: Riverside JV facility of GBP 8500000, LTV covenant 65 percent.",
                "Investment Memo: Logistics Portfolio, 6.2 percent yield, 14 percent IRR."
        ));

        String question = "What is the LTV covenant on the Riverside JV loan?";
        List<Document> ranked = pipeline.rerank(question);
        for (Document doc : ranked) {
            System.out.println(doc);
        }
    }
}