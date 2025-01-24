package de.project.chainofresponsibility;

public class EconomyHandler extends SeatReservationHandler {
    @Override
    public void handleReservation(String seatClass) {
        if ("Economy".equalsIgnoreCase(seatClass)) {
            System.out.println("Handling reservation for Economy class.");
        } else if (nextHandler != null) {
            nextHandler.handleReservation(seatClass);
        } else {
            System.out.println("No handler found for " + seatClass + ".");
        }
    }
}
