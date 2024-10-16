class Stack {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public Stack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int value) {
        if (top < maxSize - 1) {
            stackArray[++top] = value;
            System.out.println("Pushed " + value + " to stack.");
        } else {
            System.out.println("Stack is full. Cannot push " + value);
        }
    }

    public int pop() {
        if (top >= 0) {
            int value = stackArray[top--];
            System.out.println("Popped " + value + " from stack.");
            return value;
        } else {
            System.out.println("Stack is empty. Cannot pop.");
            return -1; // or throw an exception
        }
    }
    // Add this method inside the Stack class
    public int peek() {
        if (top >= 0) {
            return stackArray[top];
        } else {
            System.out.println("Stack is empty. Cannot peek.");
            return -1; // or throw an exception
        }
    }
    // Add these methods inside the Stack class
    public boolean isEmpty() {
        return top == -1; // Returns true if the stack is empty
    }

    public boolean isFull() {
        return top == maxSize - 1; // Returns true if the stack is full
    }

}

public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element (peek): " + stack.peek()); // Should return 30

        stack.pop(); // Remove the top element
        System.out.println("Top element after pop (peek): " + stack.peek()); // Should return 20

        stack.pop(); // Remove another element
        stack.pop(); // Remove last element
        stack.pop(); // Attempt to pop from an empty stack
    }
}
