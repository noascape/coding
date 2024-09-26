import Automat.*;
import Enumeration.Manufacturer;
import ProcessingUnit.ProcessingUnit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        int serialNumber = 99292833;

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String lastDateOfFill = currentDate.format(formatter);

        Automat automat = new Automat(Manufacturer.AB, serialNumber, lastDateOfFill);

        for (Compartement compartement : automat.getCompartements()) {
            compartement.serveDrink();
        }
    }
}