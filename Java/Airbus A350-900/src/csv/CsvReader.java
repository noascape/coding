package csv;

import human.Passenger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {

    // Methode zum Lesen der CSV-Datei und Speichern der Daten in einer ArrayList von Passagieren
    public static ArrayList<Passenger> readCsvForPassengers(String csvFile, String csvSeparator) {
        ArrayList<Passenger> passengers = new ArrayList<>();
        String line;
        boolean isFirstLine = true;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // Überspringe die Kopfzeile
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split(csvSeparator);
                if (columns.length > 0) {
                    String key = columns[0];
                    String name = columns.length > 2 ? columns[2] : "";
                    String passportID = columns.length > 5 ? columns[5] : "";
                    int countCheckedBaggage = columns.length > 16 ? Integer.parseInt(columns[16]) : 0;
                    String checkedBaggage = columns.length > 17 ? columns[17] : "";
                    boolean hasWarrant = columns.length > 18 && columns[18].equalsIgnoreCase("yes");

                    // Erstelle einen neuen Passagier
                    Passenger passenger = new Passenger(key, name, passportID, countCheckedBaggage, checkedBaggage, hasWarrant);

                    // Füge die Ticketinformationen hinzu
                    if (columns.length > 8) passenger.addTicket("bookingID", columns[8]);
                    if (columns.length > 9) passenger.addTicket("flight", columns[9]);
                    if (columns.length > 10) passenger.addTicket("from", columns[10]);
                    if (columns.length > 11) passenger.addTicket("to", columns[11]);
                    if (columns.length > 12) passenger.addTicket("departure", columns[12]);
                    if (columns.length > 13) passenger.addTicket("arrival", columns[13]);
                    if (columns.length > 14) passenger.addTicket("bookingClass", columns[14]);
                    if (columns.length > 15) passenger.addTicket("seat", columns[15]);

                    passengers.add(passenger);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return passengers;
    }
}
