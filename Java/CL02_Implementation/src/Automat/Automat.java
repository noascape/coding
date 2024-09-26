package Automat;

import Enumeration.*;
import ProcessingUnit.Core;
import ProcessingUnit.ProcessingUnit;
import ProcessingUnit.Processor;

public class Automat {
    private int SerialNumber;
    private Manufacturer manufacturer;
    private String LastDateOfFill;
    private final OutputTray outputTray;
    private final Scanner scanner;
    private final Compartement[] compartements;


    public Automat(Manufacturer manufacturer, int serialNumber, String lastDateOfFill) {
        this.SerialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.LastDateOfFill = lastDateOfFill;
        this.outputTray = new OutputTray();

        Core[] cores = { new Core(), new Core() };
        Processor processor = new Processor(cores);
        Cooler cooler = new Cooler();
        this.scanner = new Scanner (processor, cooler);
        ProcessingUnit processingUnit = new ProcessingUnit(processor, cores);

        this.compartements = new Compartement[BeverageType.values().length];

        for (int i = 0; i < compartements.length; i++) {
            BeverageType beverageType = BeverageType.values()[i];
            compartements[i] = new Compartement(beverageType);
        }
    }


    //getter and setter for later
    public int getSerialNumber() {
        return this.SerialNumber;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getLastDateOfFill() {
        return this.LastDateOfFill;
    }

    public OutputTray getOutputTray() {
        return outputTray;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Compartement[] getCompartements() {
        return compartements;
    }
}
