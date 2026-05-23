public class Day42InterfaceSegregation {

    public static void main(String[] args) {

        Duck duck = new Duck("Donald");
        Fish fish = new Fish("Nemo");
        Eagle eagle = new Eagle("Eddie");

        System.out.println("=== Creature abilities ===\n");

        Animal[] animals = {duck, fish, eagle};
        for (Animal a : animals) {
            System.out.println("  " + a.getName() + ":");
            if (a instanceof Flyable) {
                System.out.println("    " + ((Flyable) a).fly());
            }
            if (a instanceof Swimmable) {
                System.out.println("    " + ((Swimmable) a).swim());
            }
            if (a instanceof Walkable) {
                System.out.println("    " + ((Walkable) a).walk());
            }
        }

        System.out.println("\n=== instanceof checks ===\n");
        System.out.println("  Duck instanceof Flyable  : " + (duck instanceof Flyable));
        System.out.println("  Fish instanceof Flyable  : " + (fish instanceof Flyable));
        System.out.println("  Eagle instanceof Swimmable: " + (eagle instanceof Swimmable));

    }

}

interface Flyable {
    String fly();
}

interface Swimmable {
    String swim();
}

interface Walkable {
    String walk();
}

abstract class Animal {
    protected String name;
    public Animal(String name) { this.name = name; }
    public String getName() { return name; }
}

class Duck extends Animal implements Flyable, Swimmable, Walkable {
    public Duck(String name) { super(name); }
    public String fly()  { return name + " is flying."; }
    public String swim() { return name + " is swimming."; }
    public String walk() { return name + " is walking."; }
}

class Fish extends Animal implements Swimmable {
    public Fish(String name) { super(name); }
    public String swim() { return name + " is swimming."; }
}

class Eagle extends Animal implements Flyable, Walkable {
    public Eagle(String name) { super(name); }
    public String fly()  { return name + " is soaring."; }
    public String walk() { return name + " is walking."; }
}