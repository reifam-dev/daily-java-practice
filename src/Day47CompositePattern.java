import java.util.ArrayList;

public class Day47CompositePattern {

    public static void main(String[] args) {

        Folder root = new Folder("root");
        Folder docs = new Folder("documents");
        Folder images = new Folder("images");

        docs.add(new File("report.pdf", 500));
        docs.add(new File("notes.txt", 20));
        images.add(new File("photo.jpg", 2000));
        images.add(new File("logo.png", 150));
        root.add(docs);
        root.add(images);
        root.add(new File("readme.txt", 5));

        System.out.println("=== File System Tree ===\n");
        root.display(0);

        System.out.println("\nTotal size    : " + root.getSize() + " KB");
        System.out.println("Docs size     : " + docs.getSize() + " KB");

    }

}

interface FileSystemItem {
    int getSize();
    void display(int indent);
    String getName();
}

class File implements FileSystemItem {

    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() { return name; }
    public int getSize() { return size; }

    public void display(int indent) {
        System.out.println(" ".repeat(indent) + "File: " + name
                + " (" + size + " KB)");
    }

}

class Folder implements FileSystemItem {

    private String name;
    private ArrayList<FileSystemItem> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void add(FileSystemItem item) {
        children.add(item);
    }

    public int getSize() {
        int total = 0;
        for (FileSystemItem child : children) {
            total += child.getSize();
        }
        return total;
    }

    public void display(int indent) {
        System.out.println(" ".repeat(indent) + "Folder: " + name
                + "/ (" + getSize() + " KB)");
        for (FileSystemItem child : children) {
            child.display(indent + 4);
        }
    }

}