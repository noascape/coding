package de.project.state;

public class OccupiedState implements SeatState {
    @Override
    public void handleState(SeatContext context) {
        System.out.println("Seat is now occupied.");
        context.setState(this);
    }

    @Override
    public String getStateName() {
        return "Occupied";
    }
}
