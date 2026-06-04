import java.util.ArrayList;

public class Day55TypingGenerics {

    public static void main(String[] args) {

        System.out.println("=== Generic Stack<Integer> ===\n");
        GenericStack<Integer> intStack = new GenericStack<>();
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);
        System.out.println("  Stack  : " + intStack);
        System.out.println("  Peek   : " + intStack.peek());
        System.out.println("  Pop    : " + intStack.pop());
        System.out.println("  After  : " + intStack);
        System.out.println("  Size   : " + intStack.size());

        System.out.println("\n=== Generic Stack<String> ===\n");
        GenericStack<String> strStack = new GenericStack<>();
        strStack.push("Hello");
        strStack.push("World");
        System.out.println("  Stack  : " + strStack);
        System.out.println("  Pop    : " + strStack.pop());

        System.out.println("\n=== Empty stack guard ===\n");
        try {
            GenericStack<Integer> empty = new GenericStack<>();
            empty.pop();
        } catch (RuntimeException e) {
            System.out.println("  Error  : " + e.getMessage());
        }

    }

}

class GenericStack<T> {

    private ArrayList<T> items = new ArrayList<>();

    public void push(T item) {
        items.add(item);
    }

    public T pop() {
        if (items.isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        return items.remove(items.size() - 1);
    }

    public T peek() {
        if (items.isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        return items.get(items.size() - 1);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    @Override
    public String toString() {
        return "Stack" + items.toString();
    }

}