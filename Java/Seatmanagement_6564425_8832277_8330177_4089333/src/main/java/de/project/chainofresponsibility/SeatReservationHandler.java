package de.project.chainofresponsibility;

public abstract class SeatReservationHandler {
    protected SeatReservationHandler nextHandler;

    public void setNextHandler(SeatReservationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleReservation(String seatClass);
}
