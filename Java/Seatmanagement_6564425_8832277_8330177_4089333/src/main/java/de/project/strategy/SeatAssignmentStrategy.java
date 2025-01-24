package de.project.strategy;

import java.util.List;

public interface SeatAssignmentStrategy {
    String assignSeat(List<String> availableSeats);
}
