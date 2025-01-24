package de.project.command;

import de.project.command.receiver.SeatReceiver;

public class CancelSeatCommand implements Command {
    private final SeatReceiver seatReceiver;
    private final String seatId;

    public CancelSeatCommand(SeatReceiver seatReceiver, String seatId) {
        this.seatReceiver = seatReceiver;
        this.seatId = seatId;
    }

    @Override
    public void execute() {
        seatReceiver.cancelSeat(seatId);
    }
}
