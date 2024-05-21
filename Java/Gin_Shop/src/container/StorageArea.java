package container;

import java.util.Stack;


public class StorageArea {
    private Stack<Box> stack;

    public StorageArea() {
        stack = new Stack<>();
    }

    public void addBox(Box box) {
        stack.push(box);
    }

    public Box removeBox() {
        return stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public Stack<Box> getStack() {
        return stack;
    }

    public void setStack(Stack<Box> stack) {
        this.stack = stack;
    }

}
