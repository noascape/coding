package checkIn;

import human.Passenger;

public class FederalPoliceOfficer {
    public void arrest(Passenger passenger) {
        System.out.println("Passenger " + passenger.getName() + " is arrested and baggage confiscated.");
    }
}
