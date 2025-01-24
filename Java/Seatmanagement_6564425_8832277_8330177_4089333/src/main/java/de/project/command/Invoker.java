package de.project.command;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;

public class Invoker {
    private static final Logger LOGGER = Logger.getLogger(Invoker.class.getName());
    private final Deque<Command> commandHistory = new ArrayDeque<>();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (commandHistory.isEmpty()) {
            LOGGER.warning("No commands to undo.");
        } else {
            Command lastCommand = commandHistory.pop();
            LOGGER.info(() -> "Undoing command: " + lastCommand.getClass().getSimpleName());
            // Add undo logic here if supported by commands
        }
    }

    public void showHistory() {
        LOGGER.info("Command History:");
        commandHistory.forEach(command -> LOGGER.info(command.getClass().getSimpleName()));
    }
}
