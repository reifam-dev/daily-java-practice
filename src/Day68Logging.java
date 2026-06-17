import java.util.logging.*;
import java.io.*;

public class Day68Logging {

    private static final Logger logger = Logger.getLogger(Day68Logging.class.getName());

    static {
        logger.setLevel(Level.ALL);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                return String.format("[%s] %s: %s%n",
                        record.getLevel(),
                        record.getLoggerName(),
                        record.getMessage());
            }
        });
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
    }

    public static void main(String[] args) {

        System.out.println("=== Logging levels ===\n");
        logger.finest("FINEST — most detailed");
        logger.fine("FINE — debug detail");
        logger.info("INFO — general message");
        logger.warning("WARNING — potential issue");
        logger.severe("SEVERE — serious error");

        System.out.println("\n=== DataProcessor with logging ===\n");
        DataProcessor processor = new DataProcessor("Finance");
        processor.process(42.5);
        processor.process(-10.0);
        processor.process(2000.0);

    }

}

class DataProcessor {

    private String name;
    private static final Logger log = Logger.getLogger(DataProcessor.class.getName());
    private static final double MAX_VALUE = 1000.0;

    public DataProcessor(String name) {
        this.name = name;
    }

    public double process(double value) {
        log.fine("Processing value: " + value);

        if (value < 0) {
            log.warning("Negative value: " + value + ". Using absolute.");
            value = Math.abs(value);
        }

        if (value > MAX_VALUE) {
            log.severe("Value " + value + " exceeds maximum " + MAX_VALUE);
            return -1;
        }

        double result = value * 2;
        log.info(name + " processed " + value + " → " + result);
        return result;
    }

}