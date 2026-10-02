import java.util.List;
import java.util.Map;

public class Day175ErrorQuiz {
    private static final Map<String, List<String>> VALID_TRANSITIONS = Map.of(
            "draft", List.of("submitted"),
            "submitted", List.of("approved", "rejected"),
            "approved", List.of("funded"),
            "rejected", List.of(),
            "funded", List.of()
    );

    private String state;

    public Day175ErrorQuiz(String initialState) {
        this.state = initialState;
    }

    public boolean transitionTo(String newState) {
        state = newState
        return true;
    }

    public static void main(String[] args) {
        Day175ErrorQuiz deal = new Day175ErrorQuiz("draft");
        System.out.println(deal.transitionTo("submitted"));
        System.out.println(deal.transitionTo("funded"));
        System.out.println(deal.state);
    }
}