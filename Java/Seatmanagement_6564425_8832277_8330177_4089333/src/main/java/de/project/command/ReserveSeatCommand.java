package de.project.command;

import de.project.command.receiver.SeatReceiver;

public class ReserveSeatCommand implements Command {
    private final SeatReceiver seatReceiver;
    private final String seatId;
    private final String passengerName;

    public ReserveSeatCommand(SeatReceiver seatReceiver, String seatId, String passengerName) {
        this.seatReceiver = seatReceiver;
        this.seatId = seatId;
        this.passengerName = passengerName;
    }

    @Override
    public void execute() {
        seatReceiver.reserveSeat(seatId, passengerName);
    }
}
