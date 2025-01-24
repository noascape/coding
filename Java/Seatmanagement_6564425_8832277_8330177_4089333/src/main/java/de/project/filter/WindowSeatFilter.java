package de.project.filter;

import java.util.List;
import java.util.stream.Collectors;

public class WindowSeatFilter implements SeatFilter {
    @Override
    public List<Seat> filter(List<Seat> seats) {
        return seats.stream()
                .filter(Seat::isWindowSeat)
                .collect(Collectors.toList());
    }
}
