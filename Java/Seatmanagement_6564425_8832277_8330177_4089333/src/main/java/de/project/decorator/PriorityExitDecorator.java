package de.project.decorator;

public class PriorityExitDecorator extends SeatDecorator {
    public PriorityExitDecorator(Seat decoratedSeat) {
        super(decoratedSeat);
    }

    @Override
    public String getDescription() {
        return decoratedSeat.getDescription() + ", Priority Exit";
    }

    @Override
    public double getCost() {
        return decoratedSeat.getCost() + 15.0; // Zusatzkosten
    }
}
