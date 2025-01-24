package de.project.strategy;

import java.util.List;

public class FirstAvailableStrategy implements SeatAssignmentStrategy {
    @Override
    public String assignSeat(List<String> availableSeats) {
        if (!availableSeats.isEmpty()) {
            return availableSeats.removeFirst(); // Nimmt den ersten verfügbaren Sitz
        }
        return "No seats available.";
    }
}
