package main;

import java.io.IOException;
import java.util.*;
import Boarding.BoardingProcess;
import checkIn.CheckInProcess;
import checkIn.StorageArea;
import checkIn.WaitingArea;
import csv.CsvReader;
import human.Passenger;


public class Application {
    public static void main(String[] args) {
            String csvFile = "../Airbus A350-900/src/data.csv"; // Ersetze mit dem Pfad zu deiner CSV-Datei
            String csvSeparator = ",";

        // Lese die CSV-Datei und speichere die Daten in einer ArrayList von Passagieren
        ArrayList<Passenger> passengers = CsvReader.readCsvForPassengers(csvFile, csvSeparator);

        // Initialisiere die Lagerfläche für Gepäck
        StorageArea storageArea = new StorageArea(10, 10, 5);

        // Initialisiere den Check-In-Prozess
        CheckInProcess checkInProcess = new CheckInProcess(storageArea);

        // Füge die Passagiere zu den Warteschlangen der Check-In-Schalter hinzu
        for (Passenger passenger : passengers) {
            checkInProcess.addPassengerToQueue(passenger);
        }

        // Starte den Check-In-Prozess
        checkInProcess.startCheckIn();

        // Initialisiere den Warteraum
        WaitingArea waitingArea = new WaitingArea(20, 30);

        // Filtern der Passagiere ohne Haftbefehl und hinzufügen zum Warteraum
        List<Passenger> allowedPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (!passenger.hasWarrant()) {
                waitingArea.addPassenger(passenger);
                allowedPassengers.add(passenger);
            }
        }

        // Initialisiere den Boarding-Prozess
        BoardingProcess boardingProcess = new BoardingProcess();

        // Initialisiere die Boarding-Warteschlange
        boardingProcess.initializeBoardingQueue(new ArrayList<>(allowedPassengers));

        // Starte das Boarding
        boardingProcess.startBoarding();

        // Generiere die Berichte
        try {
            boardingProcess.generateReports(new ArrayList<>(allowedPassengers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}