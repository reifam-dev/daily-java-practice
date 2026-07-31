import java.util.ArrayList;
import java.util.List;

class Document {
    private String text;
    private int id;

    public Document(String text, int id) {
        text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }
}

public class Day112ErrorQuiz {

    private static List<Document> documents = new ArrayList<>();

    static {
        documents.add(new Document(
                "Loan Agreement: Riverside JV facility of GBP 8500000, LTV covenant 65 percent.", 0));
        documents.add(new Document(
                "Investment Memo: Logistics Portfolio, 6.2 percent yield, 14 percent IRR.", 1));
    }

    private static int overlapScore(String question, String doc) {
        String[] questionWords = question.toLowerCase().split(" ");
        String[] docWords = doc.toLowerCase().split(" ")
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

    private static List<Document> rerank(String question) {
        List<Document> ranked = new ArrayList<>(documents);
        ranked.sort((a, b) -> overlapScore(question, a.getText()) - overlapScore(question, b.getText()));
        return ranked;
    }

    public static void main(String[] args) {
        String question = "What is the LTV covenant on the Riverside JV loan?";
        List<Document> ranked = rerank(question);
        for (Document doc : ranked) {
            System.out.println("[" + doc.getId() + "] " + doc.getText());
        }
    }
}