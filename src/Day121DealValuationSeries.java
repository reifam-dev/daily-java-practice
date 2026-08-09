import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Resamples an irregular daily valuation series to a continuous daily
 * range and forward-fills gaps with the last known value - a Java
 * analogue of the pandas resample/ffill pattern.
 */
public class Day121DealValuationSeries {

    private static Map<LocalDate, Double> fillGaps(Map<LocalDate, Double> raw) {
        Map<LocalDate, Double> filled = new LinkedHashMap<>();

        LocalDate start = null;
        LocalDate end = null;
        for (LocalDate date : raw.keySet()) {
            if (start == null || date.isBefore(start)) {
                start = date;
            }
            if (end == null || date.isAfter(end)) {
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
            current = current.plusDays(1);
        }

        return filled;
    }

    public static void main(String[] args) {
        Map<LocalDate, Double> raw = new LinkedHashMap<>();
        raw.put(LocalDate.of(2026, 1, 1), 100.0);
        raw.put(LocalDate.of(2026, 1, 2), 101.5);
        raw.put(LocalDate.of(2026, 1, 5), 104.0);
        raw.put(LocalDate.of(2026, 1, 6), 108.0);

        Map<LocalDate, Double> filled = fillGaps(raw);
        for (Map.Entry<LocalDate, Double> entry : filled.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}