// Day 68 - Error Finding Quiz
// Find and fix the bugs

import java.util.logging.*;

public class Day68ErrorQuiz {

    private String processorName;
    private static final Logger logger = Logger.getLogger(Day68ErrorQuiz.class.getName());

    public Day68ErrorQuiz(String processorName) {
        processorName = processorName;    // Bug 1 - missing this
    }

    public double process(double value) {
        logger.info("Processing: " + value)   // Bug 2 - missing semicolon
        if (value < 0) {
            logger.warning("Negative value: " + value);
            value = Math.abs(value);
        }
        return value * 2;
    }

    public static void main(String[] args) {
        Day68ErrorQuiz p = new Day68ErrorQuiz("Finance");
        System.out.println(p.process(42.5));
        System.out.println(p.process(-10.0));
        System.out.println(p.processorName);  // Bug 3 - field is private, cannot access directly
    }

}