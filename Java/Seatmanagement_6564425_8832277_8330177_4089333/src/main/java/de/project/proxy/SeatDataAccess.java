package de.project.proxy;

public interface SeatDataAccess {
    String getSeatData(String seatId);
    void updateSeatData(String seatId, String passengerName);
}
