package de.project.command;

import de.project.command.receiver.SeatReceiver;

public class SeatCommand implements Command {
    private final SeatReceiver seatReceiver;
    private final SeatAction action;
    private final String seatId;
    private final String passengerName; // Optional for actions like RESERVE

    public SeatCommand(SeatReceiver seatReceiver, SeatAction action, String seatId, String passengerName) {
        this.seatReceiver = seatReceiver;
        this.action = action;
        this.seatId = seatId;
        this.passengerName = passengerName;
    }

    @Override
    public void execute() {
        switch (action) {
            case RESERVE -> seatReceiver.reserveSeat(seatId, passengerName);
            case CANCEL -> seatReceiver.cancelSeat(seatId);
            default -> throw new IllegalArgumentException("Unknown action: " + action);
        }
    }
}
