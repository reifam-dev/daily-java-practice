import java.util.regex.*;
import java.util.ArrayList;

public class Day59Regex {

    public static void main(String[] args) {

        String text = "Contact alice@example.com or bob@company.co.uk. "
                + "Call +44 7700 900123 for support.";

        System.out.println("=== Email extraction ===\n");
        Pattern emailPattern = Pattern.compile(
                "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher emailMatcher = emailPattern.matcher(text);
        ArrayList<String> emails = new ArrayList<>();
        while (emailMatcher.find()) {
            emails.add(emailMatcher.group());
        }
        System.out.println("  Emails found: " + emails);

        System.out.println("\n=== Date parsing with groups ===\n");
        Pattern datePattern = Pattern.compile(
                "(\\d{4})-(\\d{2})-(\\d{2})");
        Matcher dateMatcher = datePattern.matcher("Meeting on 2026-06-08.");
        if (dateMatcher.find()) {
            System.out.println("  Year  : " + dateMatcher.group(1));
            System.out.println("  Month : " + dateMatcher.group(2));
            System.out.println("  Day   : " + dateMatcher.group(3));
        }

        System.out.println("\n=== replaceAll — clean whitespace ===\n");
        String messy = "too   many    spaces   here";
        String cleaned = messy.replaceAll("\\s+", " ").trim();
        System.out.println("  Before : '" + messy + "'");
        System.out.println("  After  : '" + cleaned + "'");

        System.out.println("\n=== matches — validation ===\n");
        String[] postcodes = {"SW1A 1AA", "EC1A 1BB", "12345", "HA9 0WS"};
        Pattern postcodePattern = Pattern.compile(
                "[A-Z]{1,2}\\d[A-Z\\d]?\\s\\d[A-Z]{2}");
        for (String pc : postcodes) {
            boolean valid = postcodePattern.matcher(pc).matches();
            System.out.println("  " + pc + " → " + (valid ? "✓" : "✗"));
        }

    }

}