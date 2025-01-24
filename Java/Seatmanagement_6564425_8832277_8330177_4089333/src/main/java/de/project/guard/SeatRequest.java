package de.project.guard;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SeatRequest {
    private final String seatId;
    private final boolean exitRow; // Gibt an, ob der Sitz ein Notausgang ist
    private final int age; // Alter der Person
}
