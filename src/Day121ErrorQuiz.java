import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class Day121ErrorQuiz {

    private static Map<LocalDate, Double> fillGaps(Map<LocalDate, Double> raw) {
        Map<LocalDate, Double> filled = new LinkedHashMap<>();

        LocalDate start = raw.keySet().iterator().next();
        LocalDate end = start;
        for (LocalDate date : raw.keySet()) {
            if (date.isAfter(end)) {
                end = date;
            }
        }

        Double lastKnown = null;
        LocalDate current = start;
        while (!current.isAfter(end)) {
            if (raw.containsKey(current)) {
                lastKnown = raw.get(current);
            }
            filled.put(current, lastKnown);
            current.plusDays(1);
        }

        return filled;
    }

    public static void main(String[] args) {
        Map<LocalDate, Double> raw = new LinkedHashMap<>();
        raw.put(LocalDate.of(2026, 1, 1), 100.0);
        raw.put(LocalDate.of(2026, 1, 2), 101.5);
        raw.put(LocalDate.of(2026, 1, 5), 104.0);

        Map<LocalDate, Double> filled = fillGaps(raw);
        for (Map.Entry<LocalDate, Double> entry : filled.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}