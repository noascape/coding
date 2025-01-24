package de.project.strategy;

import java.util.List;

public class FamilyGroupingStrategy implements SeatAssignmentStrategy {
    @Override
    public String assignSeat(List<String> availableSeats) {
        if (availableSeats.size() >= 3) {
            return "Family Seats: " + availableSeats.removeFirst() + ", " +
                    availableSeats.removeFirst() + ", " +
                    availableSeats.removeFirst(); // Reserviert drei Sitze für eine Familie
        }
        return "Not enough seats available for a family.";
    }
}
