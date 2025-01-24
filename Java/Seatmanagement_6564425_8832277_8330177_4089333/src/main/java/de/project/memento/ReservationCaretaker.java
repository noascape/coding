package de.project.memento;

import lombok.RequiredArgsConstructor;

import java.util.Stack;

@RequiredArgsConstructor
public class ReservationCaretaker {
    private final Stack<ReservationMemento> mementoStack = new Stack<>();

    public void saveMemento(ReservationMemento memento) {
        mementoStack.push(memento);
        System.out.println("Saved state: " + memento);
    }

    public ReservationMemento restoreMemento() {
        if (!mementoStack.isEmpty()) {
            ReservationMemento memento = mementoStack.pop();
            System.out.println("Restored state: " + memento);
            return memento;
        }
        System.out.println("No states to restore.");
        return null;
    }
}
