package Boarding;

import human.Passenger;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BoardingQueue {
    private PriorityQueue<Passenger> queue;

    public BoardingQueue() {
        this.queue = new PriorityQueue<>(new Comparator<Passenger>() {
            @Override
            public int compare(Passenger p1, Passenger p2) {
                int bookingClassComparison = getBookingClassPriority(p1.getBookingClass()) - getBookingClassPriority(p2.getBookingClass());
                if (bookingClassComparison != 0) {
                    return bookingClassComparison;
                }
                return p1.getSeat().compareTo(p2.getSeat());
            }
        });
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

    public void addPassenger(Passenger passenger) {
        queue.add(passenger);
    }

    public Passenger pollPassenger() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
