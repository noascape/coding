package de.project.proxy;

public class SeatDataAccessProxy implements SeatDataAccess {
    private final SeatDataAccessImpl seatDataAccess;
    private final boolean isCrewMember;

    public SeatDataAccessProxy(boolean isCrewMember) {
        this.seatDataAccess = new SeatDataAccessImpl();
        this.isCrewMember = isCrewMember;
    }

    @Override
    public String getSeatData(String seatId) {
        if (isCrewMember) {
            return seatDataAccess.getSeatData(seatId);
        } else {
            throw new SecurityException("Access denied: You are not authorized to view seat data.");
        }
    }

    @Override
    public void updateSeatData(String seatId, String passengerName) {
        if (isCrewMember) {
            seatDataAccess.updateSeatData(seatId, passengerName);
        } else {
            throw new SecurityException("Access denied: You are not authorized to update seat data.");
        }
    }
}
