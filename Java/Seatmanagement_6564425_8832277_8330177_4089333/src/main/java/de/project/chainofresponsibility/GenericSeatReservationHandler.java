package de.project.chainofresponsibility;

import java.util.logging.Logger;

public class GenericSeatReservationHandler extends SeatReservationHandler {
    private static final Logger LOGGER = Logger.getLogger(GenericSeatReservationHandler.class.getName());
    private final SeatClass supportedSeatClass;

    public GenericSeatReservationHandler(SeatClass supportedSeatClass) {
        this.supportedSeatClass = supportedSeatClass;
    }

    @Override
    public void handleReservation(String seatClass) {
        try {
            SeatClass classEnum = SeatClass.fromString(seatClass);
            if (classEnum == supportedSeatClass) {
                LOGGER.info(() -> "Handling reservation for " + supportedSeatClass.name().replace("_", " ") + " class.");
            } else if (nextHandler != null) {
                nextHandler.handleReservation(seatClass);
            } else {
                LOGGER.warning(() -> "No handler found for " + seatClass + ".");
            }
        } catch (IllegalArgumentException e) {
            LOGGER.severe(() -> "Invalid seat class provided: " + seatClass);
        }
    }
}
