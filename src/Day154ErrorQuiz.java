import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day154ErrorQuiz {
    private static void visit(String node, Map<String, List<String>> deps, List<String> result) {
        for (String dep : deps.getOrDefault(node, new ArrayList<>())) {
            visit(dep, deps, result);
        }
        result.add(node)
    }

    private static List<String> resolveOrder(Map<String, List<String>> dependencies) {
        List<String> result = new ArrayList<>();
        for (String node : dependencies.keySet()) {
            visit(node, dependencies, result);
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