final class DealExtract {
    private final String dealName;
    private final double marketValue;
    private final double ltv;
    private final String sector;

    public DealExtract(String dealName, double marketValue, double ltv, String sector) {
        this.dealName = dealName;
        this.marketValue = marketValue;
        this.ltv = ltv;
        this.sector = sector;
    }

    public String getDealName() {
        return this.dealName;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    public double getLtv() {
        return this.ltv;
    }

    public String getSector() {
        return this.sector;
    }

    @Override
    public String toString() {
        return "DealExtract{name='" + this.dealName + "', marketValue=" + this.marketValue
                + ", ltv=" + this.ltv + ", sector='" + this.sector + "'}";
    }
}

final class ExtractionException extends RuntimeException {
    public ExtractionException(String message) {
        super(message);
    }
}

/**
 * Parses a structured deal record from a comma-separated line, a
 * simplified Java analogue of the Python Pydantic-schema extraction.
 */
public class Day104DealExtractor {

    private static final int EXPECTED_FIELD_COUNT = 4;

    private static DealExtract parseDealLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != EXPECTED_FIELD_COUNT) {
            throw new ExtractionException("Expected " + EXPECTED_FIELD_COUNT
                    + " fields, got " + parts.length);
        }
        String name = parts[0].trim();
        double value = Double.parseDouble(parts[1].trim());
        double ltv = Double.parseDouble(parts[2].trim());
        String sector = parts[3].trim();
        return new DealExtract(name, value, ltv, sector);
    }

    public static void main(String[] args) {
        String raw = "Riverside JV, 12500000, 0.60, Logistics";
        DealExtract deal = parseDealLine(raw);
        System.out.println(deal);
    }
}