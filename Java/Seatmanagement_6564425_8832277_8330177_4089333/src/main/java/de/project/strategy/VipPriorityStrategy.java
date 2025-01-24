package de.project.strategy;

import java.util.List;

public class VipPriorityStrategy implements SeatAssignmentStrategy {
    @Override
    public String assignSeat(List<String> availableSeats) {
        if (!availableSeats.isEmpty()) {
            return "VIP Seat: " + availableSeats.removeFirst(); // Priorisiert VIP-Sitze
        }
        return "No seats available for VIP.";
    }
}
