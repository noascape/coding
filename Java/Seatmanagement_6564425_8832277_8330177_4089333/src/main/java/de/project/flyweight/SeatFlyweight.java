package de.project.flyweight;

import lombok.Data;

@Data
public class SeatFlyweight {
    private final String type; // z. B. "Economy", "Business"
    private final int legroom; // Beinfreiheit in cm
    private final int width;   // Sitzbreite in cm
}
