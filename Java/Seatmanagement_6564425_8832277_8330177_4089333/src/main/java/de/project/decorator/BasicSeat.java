package de.project.decorator;

public class BasicSeat implements Seat {
    @Override
    public String getDescription() {
        return "Basic Seat";
    }

    @Override
    public double getCost() {
        return 50.0; // Example cost
    }
}
