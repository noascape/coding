package de.project.visitor;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SeatGroup implements SeatElement {
    final String groupName;
    private final List<SeatElement> seats = new ArrayList<>();

    public void addSeat(SeatElement seat) {
        seats.add(seat);
    }

    @Override
    public void accept(SeatVisitor visitor) {
        for (SeatElement seat : seats) {
            seat.accept(visitor);
        }
        visitor.visit(this);
    }
}
