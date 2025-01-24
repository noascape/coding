package de.project.iterator;

import java.util.List;

public class SeatIterator {
    private final List<String> seats;
    private int position = 0;

    public SeatIterator(List<String> seats) {
        this.seats = seats;
    }

    public boolean hasNext() {
        return position < seats.size();
    }

    public String next() {
        if (hasNext()) {
            return seats.get(position++);
        }
        return null;
    }
}
