package checkIn;

import human.Passenger;

import java.util.ArrayList;

public class CheckInProcess {
    private ArrayList<CheckInDesk> checkInDesks;
    private FederalPoliceOfficer federalPoliceOfficer;

    public CheckInProcess(StorageArea storageArea) {
        this.checkInDesks = new ArrayList<>();
        this.federalPoliceOfficer = new FederalPoliceOfficer();
        for (int i = 0; i < 10; i++) {
            checkInDesks.add(new CheckInDesk(i + 1, federalPoliceOfficer, storageArea));
        }
    }

    public void addPassengerToQueue(Passenger passenger) {
        for (CheckInDesk desk : checkInDesks) {
            if (desk.getPassengerQueue().size() < 30) {
                desk.addPassenger(passenger);
                return;
            }
        }
        System.out.println("All queues are full, passenger " + passenger.getName() + " cannot be added.");
    }

    public void startCheckIn() {
        boolean morePassengers = true;
        while (morePassengers) {
            morePassengers = false;
            for (CheckInDesk desk : checkInDesks) {
                if (!desk.getPassengerQueue().isEmpty()) {
                    desk.processPassenger();
                    morePassengers = true;
                }
            }
        }
    }
}
