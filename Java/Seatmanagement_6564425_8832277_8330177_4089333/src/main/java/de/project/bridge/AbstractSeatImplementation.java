package de.project.bridge;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSeatImplementation implements SeatImplementation {
    protected final Map<String, String> seats = new HashMap<>();
    protected final AircraftType aircraftType;

    protected AbstractSeatImplementation(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    @Override
    public void assignSeat(String seatId, String passengerName) {
        seats.put(seatId, passengerName);
        System.out.println("Seat " + seatId + " assigned to " + passengerName + " (" + aircraftType.getName() + ")");
    }

    @Override
    public String getSeatDetails(String seatId) {
        return aircraftType.getName() + " Seat: " + seatId + ", Passenger: " + seats.getOrDefault(seatId, "None");
    }
}
