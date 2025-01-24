package de.project.filter;

import java.util.List;
import java.util.stream.Collectors;

public class AvailableSeatFilter implements SeatFilter {
    @Override
    public List<Seat> filter(List<Seat> seats) {
        return seats.stream()
                .filter(Seat::isAvailable)
                .collect(Collectors.toList());
    }
}
