package de.project.memento;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class SeatReservation {
    private final String seatId;
    private final String passengerName;

    // Speichert den aktuellen Zustand in ein Memento
    public ReservationMemento save() {
        return new ReservationMemento(seatId, passengerName);
    }

    // Stellt den Zustand aus einem Memento wieder her
    public void restore(ReservationMemento memento) {
        System.out.println("Restoring reservation for seat " + memento.getSeatId() +
                " to passenger " + memento.getPassengerName());
    }
}
