import csv.CsvReader;
import machines.*;
import track.Track;
import container.*;
import java.util.UUID;



public class Main {
    public static void main(String... args) {

        //implementation of the gin bottling plant
        Track bottleTrack = new Track();
        Track boxTrack = new Track();
        Track palletTrack = new Track();

        CsvReader csvReader = new CsvReader();
        csvReader.read(bottleTrack, palletTrack);

        for (int i = 0; i < 96; i++) {
            boxTrack.add(new Box(UUID.randomUUID()));
        }

        Trailer trailer = new Trailer();
        AutonomousForklift autonomousForklift = new AutonomousForklift(trailer);
        Gripper gripper = new Gripper(palletTrack, autonomousForklift);
        Roboter01 roboter01 = new Roboter01(boxTrack, gripper);
        FillingMachine fillingMachine = new FillingMachine(roboter01, new Tank(), bottleTrack);

        fillingMachine.on();
        roboter01.on();
        gripper.on();

        fillingMachine.execute();


    }
}