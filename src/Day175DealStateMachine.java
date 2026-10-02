import java.util.List;
import java.util.Map;

final class InvalidTransitionException extends RuntimeException {
    public InvalidTransitionException(String message) { super(message); }
}

/**
 * Transitions are only permitted if explicitly listed as valid from
 * the current state, preventing a deal from skipping stages or
 * re-entering a terminal state.
 */
public class Day175DealStateMachine {
    private static final Map<String, List<String>> VALID_TRANSITIONS = Map.of(
            "draft", List.of("submitted"),
            "submitted", List.of("approved", "rejected"),
            "approved", List.of("funded"),
            "rejected", List.of(),
            "funded", List.of()
    );

    private String state;

    public Day175DealStateMachine(String initialState) {
        if (!VALID_TRANSITIONS.containsKey(initialState)) {
            throw new IllegalArgumentException("Unknown state: " + initialState);
        }
        this.state = initialState;
    }

    public boolean transitionTo(String newState) {
        List<String> allowed = VALID_TRANSITIONS.getOrDefault(this.state, List.of());
        if (!allowed.contains(newState)) {
            throw new InvalidTransitionException(
                    "Cannot transition from '" + this.state + "' to '" + newState + "'");
        }
        this.state = newState;
        return true;
    }

    public String getState() {
        return this.state;
    }

    public static void main(String[] args) {
        Day175DealStateMachine deal = new Day175DealStateMachine("draft");
        System.out.println(deal.transitionTo("submitted"));
        System.out.println(deal.getState());

        try {
            deal.transitionTo("funded");
        } catch (InvalidTransitionException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}