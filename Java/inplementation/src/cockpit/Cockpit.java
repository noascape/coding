package cockpit;

import shared.Configuration;

public class Cockpit {
    private ControlStick controlStick;
    private final Seat[] seats;

    public Cockpit() {
        seats = new Seat[Configuration.INSTANCE.countSeats];
    }

    public ControlStick getControlStick() {
        return controlStick;
    }

    public void setControlStick(ControlStick controlStick) {
        this.controlStick = controlStick;
    }

    public void setSeat(Seat seat, int i) {
        seats[i] = seat;
    }
}
