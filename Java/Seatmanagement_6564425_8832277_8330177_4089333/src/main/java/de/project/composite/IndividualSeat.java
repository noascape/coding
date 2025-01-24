package de.project.composite;

public class IndividualSeat extends SeatComponent {
    private final String seatId;

    public IndividualSeat(String seatId) {
        this.seatId = seatId;
    }

    @Override
    public void displayDetails() {
        System.out.println("Seat: " + seatId);
    }
}
