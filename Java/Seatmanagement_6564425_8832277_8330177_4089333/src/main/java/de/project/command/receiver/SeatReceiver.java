package de.project.command.receiver;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SeatReceiver {
    private static final Logger LOGGER = Logger.getLogger(SeatReceiver.class.getName());
    private final Map<String, String> seatReservations = new HashMap<>();

    public void reserveSeat(String seatId, String passengerName) {
        if (seatReservations.containsKey(seatId)) {
            LOGGER.warning(() -> "Seat " + seatId + " is already reserved for " + seatReservations.get(seatId));
        } else {
            seatReservations.put(seatId, passengerName);
            LOGGER.info(() -> "Seat " + seatId + " reserved for " + passengerName);
        }
    }

    public void cancelSeat(String seatId) {
        if (seatReservations.containsKey(seatId)) {
            LOGGER.info(() -> "Reservation for seat " + seatId + " canceled.");
            seatReservations.remove(seatId);
        } else {
            LOGGER.warning(() -> "No reservation found for seat " + seatId);
        }
    }

    public void printReservations() {
        if (seatReservations.isEmpty()) {
            LOGGER.info("No reservations available.");
        } else {
            LOGGER.info("Current Reservations:");
            seatReservations.forEach((seatId, passengerName) ->
                    LOGGER.info(() -> "Seat " + seatId + ": " + passengerName));
        }
    }
}
