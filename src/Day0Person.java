public class Day0Person {
    public static void main(String[] args) {
        Person p = new Person("Jevgeni", 30);
        System.out.println(p.greet());
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String greet() {
        return "Hello! My name is " + name + " and I am " + age + " years old.";
    }
}