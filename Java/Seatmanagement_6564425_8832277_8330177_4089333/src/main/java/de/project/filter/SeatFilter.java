package de.project.filter;

import java.util.List;

public interface SeatFilter {
    List<Seat> filter(List<Seat> seats);
}
