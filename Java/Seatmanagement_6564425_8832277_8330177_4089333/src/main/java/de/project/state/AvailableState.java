package de.project.state;

public class AvailableState implements SeatState {
    @Override
    public void handleState(SeatContext context) {
        System.out.println("Seat is now available.");
        context.setState(this);
    }

    @Override
    public String getStateName() {
        return "Available";
    }
}
