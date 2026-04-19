public class Day9PasswordValidator {
    public static void main(String[] args) {
        PasswordValidator validator = new PasswordValidator("Password123");
        System.out.println("Valid: " + validator.isValid());
    }
}

class PasswordValidator {
    private String password;

    public PasswordValidator(String password) {
        this.password = password != null ? password.trim() : "";
    }

    public boolean isValid() {
        if (password.length() < 8) return false;
        if (!containsUppercase()) return false;
        if (!containsDigit()) return false;
        return true;
    }

    private boolean containsUppercase() {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) return true;
        }
        return false;
    }

    private boolean containsDigit() {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) return true;
        }
        return false;
    }
}