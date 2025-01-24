package de.project.chainofresponsibility;

public enum SeatClass {
    ECONOMY, ECONOMY_PLUS, BUSINESS, FIRST_CLASS;

    public static SeatClass fromString(String seatClass) {
        try {
            return SeatClass.valueOf(seatClass.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid seat class: " + seatClass);
        }
    }
}
