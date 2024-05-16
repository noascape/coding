package checkIn;

import human.Passenger;

import java.util.ArrayList;
import java.util.List;

public class WaitingArea {
    private int length;
    private int width;
    private boolean[][] seats; // 2D-Array, um besetzte Plätze zu verfolgen
    private List<Passenger> waitingPassengers;

    public WaitingArea(int length, int width) {
        this.length = length;
        this.width = width;
        this.seats = new boolean[length][width];
        this.waitingPassengers = new ArrayList<>();
    }

    public void addPassenger(Passenger passenger) {
        if (waitingPassengers.size() < length * width) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    if (!seats[i][j]) {
                        seats[i][j] = true;
                        waitingPassengers.add(passenger);
                        System.out.println("Passenger " + passenger.getName() + " has taken seat (" + i + ", " + j + ") in the waiting area.");
                        return;
                    }
                }
            }
        } else {
            System.out.println("No more seats available in the waiting area.");
        }
    }

    public List<Passenger> getWaitingPassengers() {
        return waitingPassengers;
    }
}
