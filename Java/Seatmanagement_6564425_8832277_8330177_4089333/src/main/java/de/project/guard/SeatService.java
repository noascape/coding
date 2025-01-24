package de.project.guard;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SeatService {
    private final SeatGuard guard;

    public void reserveSeat(SeatRequest request) {
        if (guard.validate(request)) {
            System.out.println("Seat " + request.getSeatId() + " successfully reserved for age " + request.getAge());
        } else {
            System.out.println("Seat " + request.getSeatId() + " reservation failed.");
        }
    }
}
