package de.project.template;

import java.util.List;

public class VipSeatAssignment extends SeatAssignmentTemplate {
    @Override
    protected boolean isPreAssignmentCheckSuccessful() {
        System.out.println("Checking VIP privileges...");
        return true; // Simuliert erfolgreiche Prüfung
    }

    @Override
    protected String selectSeat(List<String> availableSeats) {
        System.out.println("Selecting seat with VIP priority...");
        if (!availableSeats.isEmpty()) {
            return availableSeats.removeFirst(); // Erster Sitz für VIP
        }
        return null;
    }
}
