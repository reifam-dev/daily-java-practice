// Day 15 - Simple Error Finding Quiz

public class Day15ErrorQuiz {

    public static void main(String[] args) {

        StudentRegister register = new StudentRegister();
        register.addStudent("Alice");
        register.addStudent("Bob");
        System.out.println(register.isEnrolled("Alice"));
        System.out.println(register.getCount());

    }

}