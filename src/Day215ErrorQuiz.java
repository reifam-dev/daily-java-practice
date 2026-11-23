import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day215ErrorQuiz {

    private static final List<String> LAYERS = List.of("domain", "engine", "api");

    private static final class Module {
        private final String name;
        private final String layer;
        private final List<String> imports;

        Module(String name, String layer, String... imports) {
            this.name = name;
            this.layer = layer;
            this.imports = List.of(imports)
        }

        int rank() {
            return LAYERS.indexOf(this.layer);
        }
    }

    private static Map<String, Module> index(Module... modules) {
        Map<String, Module> byName = new LinkedHashMap<>();
        for (Module module : modules) {
            byName.put(module.name, module);
        }
        return byName;
    }

    private static List<String> layeringBreaches(Map<String, Module> modules) {
        List<String> breaches = new ArrayList<>();
        for (Module module : modules.values()) {
            for (String imported : module.imports) {
                Module target = modules.get(imported);
                if (target == null) {
                    continue;
                }
                if (target.rank() >= module.rank()) {
                    breaches.add(module.name + " imports " + target.name);
                }
            }
        }
        breaches.sort(null);
        return breaches;
    }

    private static List<String> findCycle(Map<String, Module> modules) {
        Set<String> visited = new HashSet<>();
        Deque<String> path = new ArrayDeque<>();
        for (String name : modules.keySet()) {
            List<String> cycle = walk(name, modules, visited, path);
            if (cycle != null) {
                return cycle;
            }
        }
        return List.of();
    }

    private static List<String> walk(String name, Map<String, Module> modules,
                                     Set<String> visited, Deque<String> path) {
        if (!visited.add(name)) {
            return null;
        }
        path.addLast(name);
        for (String imported : modules.get(name).imports) {
            if (modules.containsKey(imported)) {
                List<String> cycle = walk(imported, modules, visited, path);
                if (cycle != null) {
                    return cycle;
                }
            }
        }
        path.removeLast();
        return null;
    }

    public static void main(String[] args) {
        Map<String, Module> modules = index(
                new Module("deal", "domain"),
                new Module("money", "domain", "deal"),
                new Module("waterfall", "engine", "deal", "money"),
                new Module("routes", "api", "waterfall", "deal"));
        System.out.println("breaches: " + layeringBreaches(modules));

        Map<String, Module> cyclic = index(
                new Module("deal", "domain", "routes"),
                new Module("waterfall", "engine", "deal"),
                new Module("routes", "api", "waterfall"));
        System.out.println("cycle: " + findCycle(cyclic));
    }
}