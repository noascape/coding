package de.project.mediator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Passenger {
    private final Mediator mediator;

    @Getter
    private final String name;

    @Getter
    private String requestedSeat;

    public void requestSeat(String seat) {
        this.requestedSeat = seat;
        mediator.notify(this, "reserve");
    }
}
