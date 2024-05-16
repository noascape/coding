package Boarding;

import human.Passenger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class BoardingProcess {
    private BoardingQueue boardingQueue;

    public BoardingProcess() {
        this.boardingQueue = new BoardingQueue();
    }

    public void initializeBoardingQueue(ArrayList<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            boardingQueue.addPassenger(passenger);
        }
    }

    public void startBoarding() {
        while (!boardingQueue.isEmpty()) {
            Passenger passenger = boardingQueue.pollPassenger();
            System.out.println("Passenger " + passenger.getName() + " with seat " + passenger.getSeat() + " is boarding.");
            // Simulate the boarding process, e.g., moving to the airplane and taking the seat
        }
    }

    public void generateReports(ArrayList<Passenger> passengers) throws IOException {
        generateReport01(passengers);
        generateReport02(passengers);
        generateReport03(passengers);
    }

    private void generateReport01(ArrayList<Passenger> passengers) throws IOException {
        passengers.sort(new Comparator<Passenger>() {
            @Override
            public int compare(Passenger p1, Passenger p2) {
                return parseSeat(p1.getSeat()) - parseSeat(p2.getSeat());
            }
        });

        try (FileWriter writer = new FileWriter("report01.txt")) {
            for (Passenger passenger : passengers) {
                writer.write(passenger.toString() + "\n");
            }
        }
    }

    private void generateReport02(ArrayList<Passenger> passengers) throws IOException {
        passengers.sort(Comparator.comparing(Passenger::getName));
        try (FileWriter writer = new FileWriter("report02.txt")) {
            for (Passenger passenger : passengers) {
                writer.write(passenger.toString() + "\n");
            }
        }
    }

    private void generateReport03(ArrayList<Passenger> passengers) throws IOException {
        passengers.sort((p1, p2) -> {
            int bookingClassComparison = getBookingClassPriority(p1.getBookingClass()) - getBookingClassPriority(p2.getBookingClass());
            if (bookingClassComparison != 0) {
                return bookingClassComparison;
            }
            return p1.getName().compareTo(p2.getName());
        });
        try (FileWriter writer = new FileWriter("report03.txt")) {
            for (Passenger passenger : passengers) {
                writer.write(passenger.toString() + "\n");
            }
        }
    }

    private int getBookingClassPriority(String bookingClass) {
        switch (bookingClass) {
            case "BUSINESS":
                return 1;
            case "PREMIUM_ECONOMY":
                return 2;
            case "ECONOMY":
                return 3;
            default:
                return 4;
        }
    }

    private int parseSeat(String seat) {
        // Extrahiere die numerische und alphabetische Komponente des Sitzplatzes
        int rowNumber = Integer.parseInt(seat.substring(0, seat.length() - 1));
        char seatLetter = seat.charAt(seat.length() - 1);
        return rowNumber * 26 + (seatLetter - 'A');
    }
}
