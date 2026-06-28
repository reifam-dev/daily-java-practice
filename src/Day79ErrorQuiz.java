// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;

public class Day79ErrorQuiz {

    private ArrayList<String> documents;
    private String modelName;

    public Day79ErrorQuiz(String modelName) {
        modelName = modelName;                  // Bug 1: missing this
        this.documents = new ArrayList<>();
    }

    public void addDocument(String doc) {
        this.documents.add(doc);
    }

    public double cosineSim(double[] a, double[] b) {
        double dot = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < a.length; i++) {
            dot =+ a[i] * b[i];                 // Bug 2: =+ should be +=
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    public String retrieve(String query) {
        if (this.documents.isEmpty()) return "No documents."
        return this.documents.get(0);           // Bug 3: missing semicolon after return "No documents."
    }

    @Override
    public String toString() {
        return "RAG | model=" + modelName + " | docs=" + documents.size();
    }

    public static void main(String[] args) {
        Day79ErrorQuiz rag = new Day79ErrorQuiz("claude-sonnet-4-6");
        rag.addDocument("London office yields circa 4.5%.");
        rag.addDocument("Industrial yields 5-6% in Midlands.");
        System.out.println(rag);
        System.out.println(rag.retrieve("office yields"));
    }
}