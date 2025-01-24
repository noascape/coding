package de.project.state;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeatContext {
    private SeatState state;

    public SeatContext() {
        // Initialzustand ist "Available"
        this.state = new AvailableState();
    }

    public void changeState(SeatState newState) {
        newState.handleState(this);
    }

    public void printCurrentState() {
        System.out.println("Current seat state: " + state.getStateName());
    }
}
