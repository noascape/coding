package de.project.observer;

import lombok.Getter;

@Getter
public class SeatManagement extends Subject {
    public void changeSeat(String seatId, String passengerName) {
        System.out.println("Seat " + seatId + " reserved for " + passengerName);
        notifyObservers("Seat " + seatId + " has been updated. Reserved for: " + passengerName);
    }
}
