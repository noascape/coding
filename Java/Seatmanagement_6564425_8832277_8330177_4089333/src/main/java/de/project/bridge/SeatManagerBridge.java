package de.project.bridge;

public abstract class SeatManagerBridge {
    protected SeatImplementation seatImplementation;

    public SeatManagerBridge(SeatImplementation seatImplementation) {
        this.seatImplementation = seatImplementation;
    }

    public abstract void assignSeat(String seatId, String passengerName);

    public abstract String getSeatDetails(String seatId);
}
