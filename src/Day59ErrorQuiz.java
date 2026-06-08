// Day 59 - Error Finding Quiz
// Find and fix the bugs

import java.util.regex.*;

public class Day59ErrorQuiz {

    public static void main(String[] args) {

        String text = "Email: alice@example.com Phone: +44 7700 900123";

        Pattern emailPattern = Pattern.compile(
                "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        )
        Matcher emailMatcher = emailPattern.matcher(text);  // Bug 1 - missing semicolon

        while (emailMatcher.find()) {
            System.out.println("Email: " + emailMatcher.group());
        }

        String cleaned = text.replaceAll("\\s+", " ")
        System.out.println(cleaned);                         // Bug 2 - missing semicolon

        Pattern datePattern = Pattern.compile(
                "(\\d{4})-(\\d{2})-(\\d{2})"
        );
        Matcher dateMatcher = datePattern.matcher("Date: 2026-06-08");
        if (dateMatcher.find()) {
            System.out.println(dateMatcher.group(1));
            System.out.println(dateMatcher.group(2));
            System.out.println(dateMatcher.group(3));
            System.out.println(dateMatcher.group(4));        // Bug 3 - only 3 groups
        }

    }

}