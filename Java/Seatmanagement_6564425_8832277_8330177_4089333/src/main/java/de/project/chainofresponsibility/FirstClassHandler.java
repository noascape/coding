package de.project.chainofresponsibility;

public class FirstClassHandler extends SeatReservationHandler {
    @Override
    public void handleReservation(String seatClass) {
        if ("FirstClass".equalsIgnoreCase(seatClass)) {
            System.out.println("Handling reservation for First Class.");
        } else if (nextHandler != null) {
            nextHandler.handleReservation(seatClass);
        } else {
            System.out.println("No handler found for " + seatClass + ".");
        }
    }
}
