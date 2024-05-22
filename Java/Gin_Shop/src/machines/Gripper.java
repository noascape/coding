package machines;

import container.*;
import track.Track;

public class Gripper {
    private Pallet currentPallet;
    private boolean isOn;
    private Track palletTrack;
    private AutonomousForklift autonomousForklift;



    public Gripper(Track palletTrack, AutonomousForklift autonomousForklift) {
        this.palletTrack = palletTrack;
        this.currentPallet = (Pallet) palletTrack.getNext();
        this.autonomousForklift = autonomousForklift;
    }

    public void on() {
        isOn = true;
        System.out.println("Gripper turned on!");
    }

    public void off() {
        isOn = false;
        System.out.println("Gripper turned off!");
    }

    public void boxOnPallet(Box box) {
        if (!isOn){System.out.println("Gripper is off. Cant perform the task.");}

        if (currentPallet.isFull()) {
            System.out.println("Pallet is full. Gripper requests Autonomous Forklift to put the Pallet on the Trailer!");
            autonomousForklift.drive(currentPallet);
            System.out.println("Gripper requests a new Pallet.");
            currentPallet = (Pallet) palletTrack.getNext();
        }

        if (currentPallet == null) {
            System.out.println("Gripper is turning off. No Pallets available!");
            off();
            autonomousForklift.drive(currentPallet);
            return;

        }
        System.out.println("Gripper received Box and put it on the Pallet.");

        currentPallet.addBox(box);

    }
}
