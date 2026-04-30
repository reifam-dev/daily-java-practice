public class Day20Library {

    public static void main(String[] args) {

        Book book1 = new Book("1984", 3);
        Book book2 = new Book("Clean Code", 1);

        System.out.println(book1.getTitle() + " copies: " + book1.getCopies());
        System.out.println("Available: " + book1.isAvailable());

        book1.borrowBook();
        book1.borrowBook();
        System.out.println("After 2 borrows: " + book1.getCopies() + " copies left");

        book1.returnBook();
        System.out.println("After return: " + book1.getCopies() + " copies left");

        book2.borrowBook();
        book2.borrowBook();   // Should print warning - no copies left

    }

}

class Book {

    private String title;
    private int copies;

    public Book(String title, int copies) {
        this.title = title;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public int getCopies() {
        return copies;
    }

    public boolean isAvailable() {
        return copies > 0;
    }

    public void borrowBook() {
        if (copies > 0) {
            copies--;
        } else {
            System.out.println("No copies of '" + title + "' available.");
        }
    }

    public void returnBook() {
        copies++;
    }

}