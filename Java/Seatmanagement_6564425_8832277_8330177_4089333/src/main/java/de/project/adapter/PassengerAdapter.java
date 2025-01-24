package de.project.adapter;

import java.util.List;

public class PassengerAdapter {
    private final PassengerDatabase passengerDatabase;

    public PassengerAdapter(PassengerDatabase passengerDatabase) {
        this.passengerDatabase = passengerDatabase;
    }

    // Converts passenger data from the database format to the SeatManager format
    public void adaptData(SeatManager seatManager) {
        List<String> passengers = DataConversionUtils.commaSeparatedStringToList(passengerDatabase.getPassengerData());
        passengers.forEach(seatManager::addPassenger);
    }
}
