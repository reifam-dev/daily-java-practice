// Day 10 - Simple Error Finding Quiz

public class Day10ErrorQuiz {
    public static void main(String[] args) {
        EmailValidator validator = new EmailValidator("test@example.com");
        System.out.println(validator.isValid());
    }
}