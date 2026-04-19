public class Day10EmailValidator {
    public static void main(String[] args) {
        EmailValidator validator = new EmailValidator("test@example.com");
        System.out.println("Valid: " + validator.isValid());
    }
}

class EmailValidator {
    private String email;

    public EmailValidator(String email) {
        this.email = email != null ? email.trim().toLowerCase() : "";
    }

    public boolean isValid() {
        if (email.isEmpty()) return false;
        if (!email.contains("@")) return false;
        if (!email.contains(".")) return false;
        return true;
    }
}