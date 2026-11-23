import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A flat source folder has no shape; packages give it one. Two rules keep
 * the shape honest: a declared layer order, so no package imports a layer
 * above its own, and no import cycles. The cycle walk needs a recursion
 * stack as well as a visited set - visited alone only says a node has been
 * seen before, never that it is still open above us on the current path.
 */
public class Day215PackageLayout {

    private static final List<String> LAYERS = List.of("domain", "engine", "api");

    private static final class Module {
        private final String name;
        private final String layer;
        private final List<String> imports;

        Module(String name, String layer, String... imports) {
            if (!LAYERS.contains(layer)) {
                throw new IllegalArgumentException(
                        "unknown layer " + layer + ", expected one of " + LAYERS);
            }
            this.name = name;
            this.layer = layer;
            this.imports = List.of(imports);
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
                if (target.rank() > module.rank()) {
                    breaches.add(module.name + " (" + module.layer + ") imports "
                            + target.name + " (" + target.layer + ")");
                }
            }
        }
        breaches.sort(null);
        return breaches;
    }

    private static List<String> findCycle(Map<String, Module> modules) {
        Set<String> visited = new HashSet<>();
        Deque<String> path = new ArrayDeque<>();
        Set<String> onPath = new HashSet<>();
        for (String name : modules.keySet()) {
            List<String> cycle = walk(name, modules, visited, path, onPath);
            if (cycle != null) {
                return cycle;
            }
        }
        return List.of();
    }

    private static List<String> walk(String name, Map<String, Module> modules,
                                     Set<String> visited, Deque<String> path, Set<String> onPath) {
        if (onPath.contains(name)) {
            List<String> cycle = new ArrayList<>();
            boolean started = false;
            for (String step : path) {
                if (step.equals(name)) {
                    started = true;
                }
                if (started) {
                    cycle.add(step);
                }
            }
            cycle.add(name);
            return cycle;
        }
        if (!visited.add(name)) {
            return null;
        }
        path.addLast(name);
        onPath.add(name);
        for (String imported : modules.get(name).imports) {
            if (modules.containsKey(imported)) {
                List<String> cycle = walk(imported, modules, visited, path, onPath);
                if (cycle != null) {
                    return cycle;
                }
            }
        }
        path.removeLast();
        onPath.remove(name);
        return null;
    }

    public static void main(String[] args) {
        Map<String, Module> healthy = index(
                new Module("deal", "domain"),
                new Module("money", "domain"),
                new Module("waterfall", "engine", "deal", "money"),
                new Module("routes", "api", "waterfall", "deal"));
        System.out.println("breaches: " + layeringBreaches(healthy));
        System.out.println("cycle: " + findCycle(healthy));

        Map<String, Module> broken = index(
                new Module("deal", "domain", "routes"),
                new Module("waterfall", "engine", "deal"),
                new Module("routes", "api", "waterfall"));
        System.out.println("breaches: " + layeringBreaches(broken));
        System.out.println("cycle: " + String.join(" -> ", findCycle(broken)));

        try {
            new Module("routes", "transport");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}