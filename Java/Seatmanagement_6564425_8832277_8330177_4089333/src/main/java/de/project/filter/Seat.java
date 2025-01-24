package de.project.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Seat {
    private final String seatId;
    private final boolean available;
    private final boolean windowSeat;
    private final boolean wheelchairAccessible;
}
