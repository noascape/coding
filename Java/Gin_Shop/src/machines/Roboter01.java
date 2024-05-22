package machines;

import container.*;
import bottle.Bottle;
import track.Track;


public class Roboter01 {

    private Box currentBox;
    private boolean isOn;
    private Gripper gripper;
    private Track boxTrack;
    private Bottle currentBottle;

    public Roboter01(Track boxTrack, Gripper gripper) {
        this.isOn = false;
        this.boxTrack = boxTrack;
        this.currentBox = (Box) boxTrack.getNext();
        this.gripper = gripper;
    }

    public void on() {
        isOn = true;
        System.out.println("Robot01 turned on!");
    }

    public void off() {
        isOn = false;
        System.out.println("Robot01 turned off!");
    }

    public void take(Bottle bottle) {
                if(!isOn) System.out.println("Robot01 is turned off. Cant perform the task!");

                if(currentBox.isFull()) {
                    System.out.println("Box is Full. Request pickup from Gripper.");
                    gripper.boxOnPallet(currentBox);

                    System.out.println("Robot01 Trying to get a new Box.");
                    currentBox = ((Box) boxTrack.getNext());

                    if(currentBox == null) {
                        System.out.println("No Boxes available! Roboter01 is turning off!");
                        off();
                        gripper.boxOnPallet(currentBox);
                        return;
                    }

                    System.out.println("Robot01 received Box " + currentBox.hashCode());
                }

                currentBottle = bottle;
                System.out.println("Robot01 received Bottle " + currentBottle.hashCode());

                currentBox.addBottle(currentBottle);

                System.out.println("Robot01 put Bottle in Box  " + currentBox.hashCode());

    }
}