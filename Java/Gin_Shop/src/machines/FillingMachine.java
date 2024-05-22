package machines;

import bottle.Bottle;
import track.Track;
import container.Tank;

public class FillingMachine {
    private boolean isOn;
    private final Roboter01 roboter01;
    private final Tank tank;
    private final Track bottleTrack;

    public FillingMachine(Roboter01 roboter01, Tank tank, Track bottleTrack) {
        this.roboter01 = roboter01;
        this.tank = tank;
        this.bottleTrack = bottleTrack;
    }

    public void on() {
        isOn = true;
        System.out.println("Filling Machine turned on!");
    }

    public void off() {
        isOn = false;
        System.out.println("Filling Machine turned off!");
    }

    public boolean fillBottle(Bottle bottle) {

        if(!tank.drainTankAmount()) {
            System.out.println("Bottle cant be filled. Tank empty. Machine turning off!");
            off();
            return false;
        }

        bottle.fill();
        System.out.println("bottle " + bottle.hashCode() + " filled!");
        return true;
    }

    public void execute() {

        while(isOn) {
            Bottle bottle = (Bottle) bottleTrack.getNext();

            if(bottle == null) {
                System.out.println( "No bottles left. Filling Machine is turning off!");
                off();
                roboter01.take(bottle);
                return;
            }

            if(fillBottle(bottle)) roboter01.take(bottle);

        }
    }
}