package de.project.decorator;

public abstract class SeatDecorator implements Seat {
    protected final Seat decoratedSeat;

    protected SeatDecorator(Seat decoratedSeat) {
        this.decoratedSeat = decoratedSeat;
    }

    @Override
    public String getDescription() {
        return decoratedSeat.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedSeat.getCost();
    }
}
