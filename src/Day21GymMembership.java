public class Day21GymMembership {

    public static void main(String[] args) {

        Member alice = new Member("Alice");
        Member bob = new Member("Bob");

        System.out.println(alice.getName() + " active: " + alice.isActive());
        System.out.println(bob.getName() + " active: " + bob.isActive());

        alice.cancelMembership();
        System.out.println("After cancelling: "
                + alice.getName() + " active: " + alice.isActive());

        alice.reactivate();
        System.out.println("After reactivating: "
                + alice.getName() + " active: " + alice.isActive());

    }

}

class Member {

    private String name;
    private boolean active;

    public Member(String name) {
        this.name = name;
        this.active = true;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void cancelMembership() {
        active = false;
    }

    public void reactivate() {
        active = true;
    }

}