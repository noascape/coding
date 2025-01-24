package de.project.mediator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FlightAttendant {
    private final Mediator mediator;

    public void listReservations() {
        mediator.notify(this, "listReservations");
    }
}
