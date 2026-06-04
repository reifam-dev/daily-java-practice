public class Day32Zoo {

    public static void main(String[] args) {

        Animal leo = new Animal("Leo", "Lion", 5);
        Animal nemo = new Animal("Nemo", "Clownfish", 1);
        Animal ellie = new Animal("Ellie", "Elephant", 8);

        System.out.println(leo);
        System.out.println(nemo);
        System.out.println(ellie);

        System.out.println("\nIs Leo an adult: " + Animal.isAdult(leo.getAge()));
        System.out.println("Is Nemo an adult: " + Animal.isAdult(nemo.getAge()));

        nemo.haveBirthday();
        nemo.haveBirthday();
        nemo.haveBirthday();
        System.out.println("\nNemo after 3 birthdays:");
        System.out.println(nemo);
        System.out.println("Is Nemo now an adult: " + Animal.isAdult(nemo.getAge()));

    }

}

class Animal {

    private String name;
    private String species;
    private int age;

    public Animal(String name, String species, int age) {
        this.name = name;
        this.species = species;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public void haveBirthday() {
        age += 1;
    }

    public static boolean isAdult(int age) {
        return age > 3;
    }

    @Override
    public String toString() {
        return String.format("%s (%s), age %d", name, species, age);
    }

}
