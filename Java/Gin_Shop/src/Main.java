import csv.CsvReader;

public class Main {
    public static void main(String... args) {

        Bottle bottle1 = new Bottle();
        Label frontLabel = new Label();
        frontLabel.header = "Front Label Header";
        bottle1.addFrontLabel(frontLabel);

        Label backLabel = new Label();
        backLabel.header = "Back Label Header";
        bottle1.addBackLabel(backLabel);

        //implementation of the gin bottling plant
        Track bottleTrack = new Track();
        Track boxTrack = new Track();

        CsvReader csvReader = new CsvReader();
        csvReader.read();

        Roboter02 roboter02 = new Roboter02(new StorageArea());

        Roboter01 roboter01 = new Roboter01(roboter02, boxTrack);

        FillingMachine fillingMachine = new FillingMachine(roboter01, new Tank(), bottleTrack);

        fillingMachine.on();
        roboter01.on();
        roboter02.on();

        fillingMachine.execute();


    }
}