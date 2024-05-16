public class Application {
    public static void main(String... args) {

        Track bottleTrack = new Track();
        Track boxTrack = new Track();

        Identity identity = new Identity();

        for (int i = 0; i < 27; i++) {
            bottleTrack.add(new Bottle(identity));
        }

        for (int i = 0; i < 4; i++) {
            boxTrack.add(new Box());
        }

        Roboter02 roboter02 = new Roboter02(new StorageArea());

        Roboter01 roboter01 = new Roboter01(roboter02, boxTrack);

        FillingMachine fillingMachine = new FillingMachine(roboter01, new Tank(), bottleTrack);

        fillingMachine.on();
        roboter01.on();
        roboter02.on();

        fillingMachine.execute();

    }
}