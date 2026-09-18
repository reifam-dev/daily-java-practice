import java.util.HashMap;
import java.util.Map;

/**
 * Each node tracks its own logical time plus what it last knew of
 * every other node's time. merge() takes the max per node; a proper
 * happens-before check confirms causal ordering.
 */
public class Day161VectorClock {
    private final String nodeId;
    private final Map<String, Integer> clock;

    public Day161VectorClock(String nodeId, String[] allNodes) {
        this.nodeId = nodeId;
        this.clock = new HashMap<>();
        for (String node : allNodes) {
            this.clock.put(node, 0);
        }
    }

    public void tick() {
        this.clock.merge(this.nodeId, 1, Integer::sum);
    }

    public void merge(Map<String, Integer> otherClock) {
        for (Map.Entry<String, Integer> entry : otherClock.entrySet()) {
            this.clock.merge(entry.getKey(), entry.getValue(), Math::max);
        }
    }

    public boolean happensBefore(Map<String, Integer> otherClock) {
        boolean atLeastAsEarly = true;
        boolean strictlyEarlier = false;
        for (String node : this.clock.keySet()) {
            int mine = this.clock.get(node);
            int theirs = otherClock.getOrDefault(node, 0);
            if (mine > theirs) atLeastAsEarly = false;
            if (mine < theirs) strictlyEarlier = true;
        }
        return atLeastAsEarly && strictlyEarlier;
    }

    public Map<String, Integer> getClock() {
        return this.clock;
    }

    public static void main(String[] args) {
        Day161VectorClock nodeA = new Day161VectorClock("A", new String[]{"A", "B"});
        Day161VectorClock nodeB = new Day161VectorClock("B", new String[]{"A", "B"});

        nodeA.tick();
        nodeA.tick();
        System.out.println(nodeA.getClock());

        nodeB.merge(nodeA.getClock());
        nodeB.tick();
        System.out.println(nodeB.getClock());

        System.out.println(nodeA.happensBefore(nodeB.getClock()));
    }
}