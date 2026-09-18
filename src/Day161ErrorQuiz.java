import java.util.HashMap;
import java.util.Map;

public class Day161ErrorQuiz {
    private String nodeId;
    private Map<String, Integer> clock = new HashMap<>();

    public Day161ErrorQuiz(String nodeId, String[] allNodes) {
        this.nodeId = nodeId;
        for (String node : allNodes) {
            clock.put(node, 0);
        }
    }

    public void tick() {
        clock.get(nodeId) + 1
    }

    public void merge(Map<String, Integer> otherClock) {
        for (Map.Entry<String, Integer> entry : otherClock.entrySet()) {
            clock.put(entry.getKey(), entry.getValue());
        }
    }

    public Map<String, Integer> getClock() {
        return clock;
    }

    public static void main(String[] args) {
        Day161ErrorQuiz nodeA = new Day161ErrorQuiz("A", new String[]{"A", "B"});
        nodeA.tick();
        nodeA.tick();
        System.out.println(nodeA.getClock());
    }
}