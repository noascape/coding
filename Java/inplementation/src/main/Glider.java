package main;

import body.Body;
import cockpit.ControlStick;
import cockpit.Seat;
import wing.Wing;


public class Glider {
    private final Body body;

    public Glider() {
        body = new Body();
    }

    public Body getBody() {
        return new Body();
    }

    public void addControlStick(ControlStick controlStick) {
        body.addControlStick(controlStick);
    }

    public void setSeat(Seat seat, int i) {
        body.setSeat(seat, i);
    }

    public void addWings(Wing wing01, Wing wing02) {
        body.addLeftWing(wing01);
        body.addRightWing(wing02);
    }

    public void aileronUp(){
        body.aileronUp();
    }

    public void aileronDown(){
        body.aileronDown();
    }
}
