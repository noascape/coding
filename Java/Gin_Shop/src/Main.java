import container.StorageArea;
import container.Tank;
import csv.CsvReader;
import machines.FillingMachine;
import machines.Roboter01;
import machines.Roboter02;
import track.Track;
import container.Box;


public class Main {
    public static void main(String... args) {

        //implementation of the gin bottling plant
        Track bottleTrack = new Track();
        Track boxTrack = new Track();

        CsvReader csvReader = new CsvReader();
        csvReader.read(bottleTrack);

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