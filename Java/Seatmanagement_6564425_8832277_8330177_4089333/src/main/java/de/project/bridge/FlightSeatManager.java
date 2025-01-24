package de.project.bridge;

public class FlightSeatManager extends SeatManagerBridge {

    public FlightSeatManager(SeatImplementation seatImplementation) {
        super(seatImplementation);
    }

    @Override
    public void assignSeat(String seatId, String passengerName) {
        seatImplementation.assignSeat(seatId, passengerName);
    }

    @Override
    public String getSeatDetails(String seatId) {
        return seatImplementation.getSeatDetails(seatId);
    }
}
