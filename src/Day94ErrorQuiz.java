import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DocumentEntry {
    private String text;
    private String category;
    private double[] embedding;

    public DocumentEntry(String text, String category, double[] embedding) {
        text = text;
        this.category = category;
        this.embedding = embedding;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }

    public double[] getEmbedding() {
        return embedding;
    }
}

public class Day94ErrorQuiz {
    private Map<String, DocumentEntry> store = new HashMap<>();

    public void addDocument(String id, String text, String category, double[] embedding) {
        store.put(id, new DocumentEntry(text, category, embedding))
    }

    private double cosineSimilarity(double[] a, double[] b) {
        double dot = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < a.length; i++) {
            dot =+ a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    public String retrieveTopMatch(double[] queryEmbedding, String category) {
        String bestId = null;
        double bestScore = -1.0;
        for (Map.Entry<String, DocumentEntry> entry : store.entrySet()) {
            DocumentEntry doc = entry.getValue();
            if (!doc.getCategory().equals(category)) {
                continue;
            }
            double score = cosineSimilarity(queryEmbedding, doc.getEmbedding());
            if (score > bestScore) {
                bestScore = score;
                bestId = entry.getKey();
            }
        }
        return bestId == null ? null : store.get(bestId).getText();
    }

    public static void main(String[] args) {
        Day94ErrorQuiz vectorStore = new Day94ErrorQuiz();
        vectorStore.addDocument("1", "Loan Agreement: Riverside JV facility.",
                "loan_agreement", new double[]{0.9, 0.1, 0.0});
        vectorStore.addDocument("2", "Investment Memo: Logistics Portfolio.",
                "investment_memo", new double[]{0.1, 0.9, 0.0});
        String match = vectorStore.retrieveTopMatch(new double[]{0.85, 0.2, 0.0}, "loan_agreement");
        System.out.println(match);
    }
}