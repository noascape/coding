package de.project.mediator;

import java.util.HashMap;
import java.util.Map;

public class SeatMediator implements Mediator {
    private final Map<String, String> seatReservations = new HashMap<>();

    @Override
    public void notify(Object sender, String event) {
        if (sender instanceof Passenger) {
            handlePassengerEvent((Passenger) sender, event);
        } else if (sender instanceof FlightAttendant) {
            handleFlightAttendantEvent((FlightAttendant) sender, event);
        }
    }

    private void handlePassengerEvent(Passenger passenger, String event) {
        if ("reserve".equalsIgnoreCase(event)) {
            String seat = passenger.getRequestedSeat();
            if (seatReservations.containsKey(seat)) {
                System.out.println("Seat " + seat + " is already reserved.");
            } else {
                seatReservations.put(seat, passenger.getName());
                System.out.println("Seat " + seat + " reserved for " + passenger.getName());
            }
        }
    }

    private void handleFlightAttendantEvent(FlightAttendant flightAttendant, String event) {
        if ("listReservations".equalsIgnoreCase(event)) {
            System.out.println("Current Reservations:");
            seatReservations.forEach((seat, passenger) -> System.out.println("Seat " + seat + ": " + passenger));
        }
    }
}
