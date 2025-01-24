package de.project.template;

import java.util.List;

public abstract class SeatAssignmentTemplate {
    public void assignSeat(List<String> availableSeats) {
        if (isPreAssignmentCheckSuccessful()) {
            String seat = selectSeat(availableSeats);
            if (seat != null) {
                confirmAssignment(seat);
            } else {
                System.out.println("No suitable seat found.");
            }
        } else {
            System.out.println("Pre-assignment checks failed.");
        }
    }

    // Template methods to be implemented by subclasses
    protected abstract boolean isPreAssignmentCheckSuccessful();
    protected abstract String selectSeat(List<String> availableSeats);

    // Concrete method
    protected void confirmAssignment(String seat) {
        System.out.println("Seat " + seat + " assigned successfully.");
    }
}
