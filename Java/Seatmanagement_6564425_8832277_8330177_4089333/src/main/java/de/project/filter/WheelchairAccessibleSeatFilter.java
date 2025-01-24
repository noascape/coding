package de.project.filter;

import java.util.List;
import java.util.stream.Collectors;

public class WheelchairAccessibleSeatFilter implements SeatFilter {
    @Override
    public List<Seat> filter(List<Seat> seats) {
        return seats.stream()
                .filter(Seat::isWheelchairAccessible)
                .collect(Collectors.toList());
    }
}
