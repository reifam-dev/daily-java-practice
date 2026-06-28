import java.util.ArrayList;

/**
 * Day 79 – RAG system concepts in Java: document store, cosine similarity, retrieval.
 * 1Z0-811 standard: private fields, this keyword, getters, ArrayList, toString override.
 */
public class Day79SimpleRag {

    private String modelName;
    private ArrayList<String> documents;
    private ArrayList<double[]> embeddings;
    private static final int VECTOR_SIZE = 20;

    public Day79SimpleRag(String modelName) {
        this.modelName = modelName;
        this.documents = new ArrayList<>();
        this.embeddings = new ArrayList<>();
    }

    public String getModelName() { return this.modelName; }
    public int getDocumentCount() { return this.documents.size(); }

    public void addDocument(String doc) {
        this.documents.add(doc);
        this.embeddings.add(embed(doc));
    }

    private double[] embed(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        double[] vec = new double[VECTOR_SIZE];
        for (int i = 0; i < Math.min(words.length, VECTOR_SIZE); i++) {
            vec[i] = words[i].length();
        }
        return normalise(vec);
    }

    private double[] normalise(double[] vec) {
        double norm = 0.0;
        for (double v : vec) norm += v * v;
        norm = Math.sqrt(norm);
        if (norm == 0) return vec;
        double[] result = new double[vec.length];
        for (int i = 0; i < vec.length; i++) result[i] = vec[i] / norm;
        return result;
    }

    private double cosineSimilarity(double[] a, double[] b) {
        double dot = 0.0, normA = 0.0, normB = 0.0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        double denom = Math.sqrt(normA) * Math.sqrt(normB);
        return denom == 0 ? 0.0 : dot / denom;
    }

    public ArrayList<String> retrieve(String query, int k) {
        if (this.documents.isEmpty()) return new ArrayList<>();
        double[] queryVec = embed(query);
        double[] scores = new double[this.documents.size()];
        for (int i = 0; i < this.embeddings.size(); i++) {
            scores[i] = cosineSimilarity(queryVec, this.embeddings.get(i));
        }
        // Simple selection sort for top-k
        ArrayList<String> result = new ArrayList<>();
        boolean[] used = new boolean[scores.length];
        for (int t = 0; t < Math.min(k, scores.length); t++) {
            int best = -1;
            for (int i = 0; i < scores.length; i++) {
                if (!used[i] && (best == -1 || scores[i] > scores[best])) best = i;
            }
            if (best != -1) { result.add(this.documents.get(best)); used[best] = true; }
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format(
                "SimpleRAG | model=%s | documents=%d",
                this.modelName, this.documents.size()
        );
    }

    public static void main(String[] args) {
        Day79SimpleRag rag = new Day79SimpleRag("claude-sonnet-4-6");
        rag.addDocument("Prime office yields in London City are circa 4.25 to 4.75 percent.");
        rag.addDocument("Industrial logistics assets in the Midlands trade at 5.0 to 5.5 percent.");
        rag.addDocument("Retail warehousing has seen yield compression to below 5 percent.");

        System.out.println(rag);
        System.out.println("Top 2 for 'London office yields':");
        for (String doc : rag.retrieve("London office yields", 2)) {
            System.out.println("  - " + doc);
        }
    }
}