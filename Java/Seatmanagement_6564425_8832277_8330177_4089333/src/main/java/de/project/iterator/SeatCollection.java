package de.project.iterator;

import java.util.ArrayList;
import java.util.List;

public class SeatCollection {
    private final List<String> seats = new ArrayList<>();

    public void addSeat(String seat) {
        seats.add(seat);
    }

    public SeatIterator createIterator() {
        return new SeatIterator(seats);
    }
}
