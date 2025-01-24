package de.project.chainofresponsibility;

public class BusinessHandler extends SeatReservationHandler {
    @Override
    public void handleReservation(String seatClass) {
        if ("Business".equalsIgnoreCase(seatClass)) {
            System.out.println("Handling reservation for Business class.");
        } else if (nextHandler != null) {
            nextHandler.handleReservation(seatClass);
        } else {
            System.out.println("No handler found for " + seatClass + ".");
        }
    }
}
