import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class CyclicDependencyException extends RuntimeException {
    public CyclicDependencyException(String message) { super(message); }
}

public class Day154DependencyResolution {
    private static void visit(String node, Map<String, List<String>> deps,
                              Set<String> visited, Set<String> inProgress, List<String> result) {
        if (visited.contains(node)) {
            return;
        }
        if (inProgress.contains(node)) {
            throw new CyclicDependencyException("Cycle detected at: " + node);
        }

        inProgress.add(node);
        for (String dep : deps.getOrDefault(node, new ArrayList<>())) {
            visit(dep, deps, visited, inProgress, result);
        }
        inProgress.remove(node);

        visited.add(node);
        result.add(node);
    }

    private static List<String> resolveOrder(Map<String, List<String>> dependencies) {
        Set<String> visited = new HashSet<>();
        Set<String> inProgress = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (String node : dependencies.keySet()) {
            visit(node, dependencies, visited, inProgress, result);
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, List<String>> deps = new HashMap<>();
        deps.put("deploy", List.of("build", "test"));
        deps.put("build", List.of("compile"));
        deps.put("test", List.of("compile"));
        deps.put("compile", List.of());

        System.out.println(resolveOrder(deps));
    }
}