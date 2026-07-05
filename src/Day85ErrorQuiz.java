// This file contains 3 deliberate bugs. Find and fix them.
import java.util.ArrayList;
import java.util.HashMap;

public class Day85ErrorQuiz {

    private String name;
    private ArrayList<HashMap<String, String>> rows;

    public Day85ErrorQuiz(String name) {
        name = name;                            // Bug 1: missing this
        this.rows = new ArrayList<>();
    }

    public void addRow(HashMap<String, String> row) {
        this.rows.add(row);
    }

    public ArrayList<HashMap<String, String>> filterBySector(String sector) {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (HashMap<String, String> row : this.rows) {
            if (row.get("sector").equals(sector)) result.add(row)
        }                                       // Bug 2: missing semicolon
        return result;
    }

    public double meanValue() {
        double total = 0.0;
        for (HashMap<String, String> row : this.rows) {
            total =+ Double.parseDouble(row.getOrDefault("value", "0"));  // Bug 3: =+ should be +=
        }
        return this.rows.isEmpty() ? 0.0 : total / this.rows.size();
    }

    @Override
    public String toString() {
        return "DealMerger: " + name + " | rows=" + rows.size();
    }

    public static void main(String[] args) {
        Day85ErrorQuiz dm = new Day85ErrorQuiz("UK Portfolio");
        HashMap<String, String> r1 = new HashMap<>();
        r1.put("deal_id", "1"); r1.put("sector", "Office"); r1.put("value", "80.0");
        HashMap<String, String> r2 = new HashMap<>();
        r2.put("deal_id", "2"); r2.put("sector", "Retail"); r2.put("value", "30.0");
        dm.addRow(r1); dm.addRow(r2);
        System.out.println(dm);
        System.out.println("Office rows: " + dm.filterBySector("Office").size());
        System.out.println("Mean value: " + dm.meanValue());
    }
}