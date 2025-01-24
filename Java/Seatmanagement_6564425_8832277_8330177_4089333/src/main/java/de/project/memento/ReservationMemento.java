package de.project.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ReservationMemento {
    private final String seatId;
    private final String passengerName;
}
