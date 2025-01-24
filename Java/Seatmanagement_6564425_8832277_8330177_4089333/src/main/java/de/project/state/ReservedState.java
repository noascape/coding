package de.project.state;

public class ReservedState implements SeatState {
    @Override
    public void handleState(SeatContext context) {
        System.out.println("Seat is now reserved.");
        context.setState(this);
    }

    @Override
    public String getStateName() {
        return "Reserved";
    }
}
