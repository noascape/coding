package de.project.chainofresponsibility;

public class EconomyPlusHandler extends SeatReservationHandler {
    @Override
    public void handleReservation(String seatClass) {
        if ("EconomyPlus".equalsIgnoreCase(seatClass)) {
            System.out.println("Handling reservation for EconomyPlus class.");
        } else if (nextHandler != null) {
            nextHandler.handleReservation(seatClass);
        } else {
            System.out.println("No handler found for " + seatClass + ".");
        }
    }
}
