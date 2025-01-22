package shared.order;

import dp.command.Command;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PizzaOrderSystem {
    private static final int MAX_UNDO = 100;
    private final List<Command> commandHistory = new ArrayList<>();
    private final Command[] undoStack = new Command[MAX_UNDO];
    private int top = -1;

    public void placeOrder(Command command) {
        command.execute();
        commandHistory.add(command);

        if (top >= MAX_UNDO - 1) {
            throw new IllegalStateException("undo stack is full | cannot save more commands.");
        }
        undoStack[++top] = command;
    }

    public void undoLastOrder() {
        if (top >= 0) {
            Command lastCommand = undoStack[top--];
            lastCommand.undo();
        } else {
            log.info("no orders to undo.");
        }
    }

    public void showOrderHistory() {
        log.info("--- order history ---");
        commandHistory.forEach(command -> log.info("- {}", command));
    }
}
