class DealExtract {
    private String dealName;
    private double marketValue;
    private double ltv;
    private String sector;

    public DealExtract(String dealName, double marketValue, double ltv, String sector) {
        dealName = dealName;
        this.marketValue = marketValue;
        this.ltv = ltv;
        this.sector = sector;
    }

    public String getDealName() {
        return dealName;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public double getLtv() {
        return ltv;
    }

    public String getSector() {
        return sector;
    }
}

public class Day104ErrorQuiz {

    private static DealExtract parseDealLine(String line) {
        String[] parts = line.split(",");
        String name = parts[0].trim();
        double value = Double.parseDouble(parts[1].trim())
        double ltv = Double.parseDouble(parts[2].trim());
        String sector = parts[3].trim();
        return new DealExtract(name, value, ltv, sector);
    }

    public static void main(String[] args) {
        String raw = "Riverside JV, 12500000, 0.60, Logistics";
        DealExtract deal = parseDealLine(raw);
        System.out.println(deal.getDealName() + " " + deal.getMarketValue()
                + " " + deal.getLtv() + " " + deal.getSector());
    }
}