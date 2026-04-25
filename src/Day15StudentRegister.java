public class Day15StudentRegister {

    public static void main(String[] args) {

        StudentRegister register = new StudentRegister();
        register.addStudent("Alice");
        register.addStudent("Bob");
        register.addStudent("Charlie");

        System.out.println("All students : " + register.getAllStudents());
        System.out.println("Total        : " + register.getCount());
        System.out.println("Alice enrolled: " + register.isEnrolled("Alice"));

        register.removeStudent("Bob");
        System.out.println("After removing Bob: " + register.getAllStudents());

    }

}

class StudentRegister {

    private String[] students = new String[50];
    private int count = 0;

    public void addStudent(String name) {
        if (!isEnrolled(name) && count < students.length) {
            students[count] = name;
            count++;
        }
    }

    public void removeStudent(String name) {
        for (int i = 0; i < count; i++) {
            if (students[i].equals(name)) {
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[count - 1] = null;
                count--;
                return;
            }
        }
    }

    public boolean isEnrolled(String name) {
        for (int i = 0; i < count; i++) {
            if (students[i].equals(name)) return true;
        }
        return false;
    }

    public String getAllStudents() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < count; i++) {
            sb.append(students[i]);
            if (i < count - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public int getCount() {
        return count;
    }

}