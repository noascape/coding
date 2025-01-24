package de.project.factory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Seat {
    private final SeatType type;
    private final int legroom; // in cm
    private final int width;   // in cm
}
