package dp.memento;

import dp.builder.Pizza;

public class PizzaOrderCaretaker {
    private static final int MAX_HISTORY = 3;
    private final PizzaMemento[] history = new PizzaMemento[MAX_HISTORY];
    private int top = -1;

    public void save(Pizza pizza) {
        if (top >= MAX_HISTORY - 1) {
            throw new IllegalStateException("history is full. | cannot save more states.");
        }
        history[++top] = new PizzaMemento(pizza);
    }

    public Pizza undo() {
        if (top >= 0) {
            return history[top--].getPizza();
        }
        throw new IllegalStateException("no previous state to revert to.");
    }
}