package checkIn;

import human.Passenger;

import java.util.LinkedList;
import java.util.Queue;

public class CheckInDesk {
    private static int baggageSequenceId = 1; // Statische Variable für die Gepäcksequenz-ID

    private int deskId;
    private Reader passportReader;
    private Reader irisReader;
    private Reader fingerPrintReader;
    private ConveyorBelt conveyorBelt;
    private Printer printer;
    private Queue<Passenger> passengerQueue;
    private FederalPoliceOfficer federalPoliceOfficer;
    private StorageArea storageArea;

    public CheckInDesk(int deskId, FederalPoliceOfficer federalPoliceOfficer, StorageArea storageArea) {
        this.deskId = deskId;
        this.passportReader = new Reader("Passport");
        this.irisReader = new Reader("Iris");
        this.fingerPrintReader = new Reader("FingerPrint");
        this.conveyorBelt = new ConveyorBelt();
        this.printer = new Printer();
        this.passengerQueue = new LinkedList<>();
        this.federalPoliceOfficer = federalPoliceOfficer;
        this.storageArea = storageArea;
    }

    public int getDeskId() {
        return deskId;
    }

    public Queue<Passenger> getPassengerQueue() {
        return passengerQueue;
    }

    public void addPassenger(Passenger passenger) {
        if (passengerQueue.size() < 30) {
            passengerQueue.add(passenger);
        } else {
            System.out.println("Queue is full at CheckInDesk " + deskId);
        }
    }

    public void processPassenger() {
        if (!passengerQueue.isEmpty()) {
            Passenger passenger = passengerQueue.poll();
            if (passenger.hasWarrant()) {
                federalPoliceOfficer.arrest(passenger);
                return;
            }
            boolean authenticated = authenticatePassenger(passenger);
            if (authenticated) {
                handleBaggage(passenger);
                printBoardingPass(passenger);
                System.out.println("Passenger " + passenger.getName() + " has completed check-in at Desk " + deskId);
            } else {
                handleFailedAuthentication(passenger);
            }
        }
    }

    private boolean authenticatePassenger(Passenger passenger) {
        int method = (int) (Math.random() * 3);
        switch (method) {
            case 0:
                return passportReader.read(passenger);
            case 1:
                return irisReader.read(passenger);
            case 2:
                return fingerPrintReader.read(passenger);
            default:
                return false;
        }
    }

    private void handleFailedAuthentication(Passenger passenger) {
        System.out.println("Authentication failed for passenger " + passenger.getName() + ". Redirecting for further checks.");
        // Weitere Aktionen bei fehlgeschlagener Authentifizierung könnten hier hinzugefügt werden
    }

    private void handleBaggage(Passenger passenger) {
        conveyorBelt.moveForward();
        for (int i = 0; i < passenger.getCountCheckedBaggage(); i++) {
            String baggageTag = "BaggageTag-" + baggageSequenceId++;
            passenger.addTicket("BaggageTag" + (i + 1), baggageTag); // Verknüpfen des Gepäckanhängers mit dem Ticket
            printer.print(baggageTag);
            conveyorBelt.moveForward();
            if (storageArea.storeBaggage(baggageTag)) {
                System.out.println("Baggage with tag " + baggageTag + " stored by robot arm.");
            }
            conveyorBelt.moveBackward();
        }
    }

    private void printBoardingPass(Passenger passenger) {
        String boardingPass = "BoardingPass for " + passenger.getName();
        printer.print(boardingPass);
    }
}
