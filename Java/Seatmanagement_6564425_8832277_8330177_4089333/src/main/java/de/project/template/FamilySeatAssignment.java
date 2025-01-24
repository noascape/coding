package de.project.template;

import java.util.List;

public class FamilySeatAssignment extends SeatAssignmentTemplate {
    @Override
    protected boolean isPreAssignmentCheckSuccessful() {
        System.out.println("Checking for family requirements...");
        return true; // Simuliert erfolgreiche Prüfung
    }

    @Override
    protected String selectSeat(List<String> availableSeats) {
        System.out.println("Selecting seats for a family...");
        if (availableSeats.size() >= 3) {
            return availableSeats.removeFirst() + ", " +
                    availableSeats.removeFirst() + ", " +
                    availableSeats.removeFirst();
        }
        return null;
    }
}
