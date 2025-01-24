package de.project.strategy;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
public class SeatAssigner {
    private final List<String> availableSeats;
    @Setter
    private SeatAssignmentStrategy strategy;

    public void assignSeat() {
        if (strategy == null) {
            System.out.println("No strategy set for seat assignment.");
            return;
        }
        String result = strategy.assignSeat(availableSeats);
        System.out.println(result);
    }
}
