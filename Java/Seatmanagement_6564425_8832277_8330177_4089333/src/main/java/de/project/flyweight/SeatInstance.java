package de.project.flyweight;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SeatInstance {
    private final String seatId; // Eindeutige ID des Sitzplatzes
    private final SeatFlyweight seatFlyweight; // Gemeinsame Daten des Sitzplatzes
}
