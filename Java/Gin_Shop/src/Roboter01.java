public class Roboter01 {

    private Box currentBox;
    private boolean isOn;
    private Roboter02 roboter02;
    private Track boxTrack;
    private Bottle currentBottle;

    public Roboter01(Roboter02 roboter02, Track boxTrack) {
        this.isOn = false;
        this.roboter02 = roboter02;
        this.boxTrack = boxTrack;
        this.currentBox = (Box) boxTrack.getNext();
    }

    public void on() {
        isOn = true;
        System.out.println("Robot 01 turned on!");
    }

    public void off() {
        isOn = false;
        System.out.println("Robot 01 turned off!");
    }

    public void take(Bottle bottle) {
                if(!isOn) System.out.println("Roboter01 is turned off. Cant perform a take");

                if(currentBox.isFull()) {
                    System.out.println("Box of Roboter01 is Full. Request pickup from Robot 02");
                    roboter02.takeBox(currentBox);

                    System.out.println("Roboter01 Trying to get a new Box");
                    currentBox = ((Box) boxTrack.getNext());

                    if(currentBox == null) {
                        System.out.println("No Boxes available! Roboter01 is turning off!");
                        isOn = false;
                        return;
                    }

                    System.out.println("Roboter 01 received Box " + currentBox.hashCode());
                }

                currentBottle = bottle;
                    System.out.println("Roboter 01 received Bottle " + currentBottle.hashCode());

                currentBox.addBottle(currentBottle);

                System.out.println("Roboter 01 put Bottle in Box  " + currentBox.hashCode());

    }
}