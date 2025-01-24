package de.project.visitor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class IndividualSeat implements SeatElement {
    private final String seatId;
    private final String seatClass; // z. B. "Economy", "Business", "FirstClass"

    @Override
    public void accept(SeatVisitor visitor) {
        visitor.visit(this);
    }
}
