package de.project.adapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SeatManager {
    private final List<String> passengerList;

    public SeatManager() {
        this.passengerList = new ArrayList<>();
    }

    // Adds a single passenger to the list
    public void addPassenger(String passenger) {
        passengerList.add(passenger);
    }

    // Gets the list of passengers
    public List<String> getPassengerList() {
        return new ArrayList<>(passengerList);
    }
}
