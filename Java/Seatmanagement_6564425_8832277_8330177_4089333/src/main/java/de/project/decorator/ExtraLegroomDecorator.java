package de.project.decorator;

public class ExtraLegroomDecorator extends SeatDecorator {
    public ExtraLegroomDecorator(BasicSeat decoratedSeat) {
        super(decoratedSeat);
    }

    @Override
    public String getDescription() {
        return decoratedSeat.getDescription() + ", Extra Legroom";
    }

    @Override
    public double getCost() {
        return decoratedSeat.getCost() + 20.0; // Additional cost for extra legroom
    }

    public Seat getSeat(){
        return decoratedSeat;
    }
}
