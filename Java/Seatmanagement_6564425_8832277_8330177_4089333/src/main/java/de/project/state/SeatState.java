package de.project.state;

public interface SeatState {
    void handleState(SeatContext context);
    String getStateName();
}
