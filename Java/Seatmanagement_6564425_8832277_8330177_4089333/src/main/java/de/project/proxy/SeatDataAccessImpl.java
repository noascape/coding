package de.project.proxy;

import java.util.HashMap;
import java.util.Map;

public class SeatDataAccessImpl implements SeatDataAccess {
    private final Map<String, String> seatData = new HashMap<>();

    @Override
    public String getSeatData(String seatId) {
        return seatData.getOrDefault(seatId, "Seat is unoccupied");
    }

    @Override
    public void updateSeatData(String seatId, String passengerName) {
        seatData.put(seatId, passengerName);
        System.out.println("Seat " + seatId + " updated with passenger " + passengerName);
    }
}
