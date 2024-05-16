package main;

import body.Body;
import wing.Wing;

import java.time.LocalDate;

public class Airplane {
    private final Body body;
    private  String manufacturer = "Airbus"; // (Airbus)
    private  String model = "350-900"; // (350-900)
    private  double overallLength = 66.89; // (66.89 m)
    private  double cabinLength = 51.04; // (51.04 m)
    private  double wingSpan = 64.75; // (64.75 m)
    private  double height = 17.05; // (17.05 m)
    private  double range = 15372; // (15 372 km)
    private  String mmo = "M0.89"; // (M0.89)
    private  double maximumTakeoffWeight = 268; // (268 t)
    private  String enginePower = "2 x 84,000 lb"; // (2 x 84,000 lb)
    private  double cruisingSpeed = 0.85; // (0.85 Mach)
    private  double fuelConsumption = 2.9; // (ca. 2.9 l/100 paxkm)
    private  int numBusinessClass = 48; // (48)
    private  int numPremiumEconomy = 21; // (21)
    private  int numEconomy = 224; // (224)
    private  String serialNumber = "S9VNG45XPI"; // (S9VNG45XPI)
    private  String id = "D-AIXD"; // (D-AIXD)
    private String carrier = "Lufthansa"; // (Lufthansa)
    private  LocalDate manufacturingDate = LocalDate.of(2023, 11, 22); // (22.11.2023)
    private LocalDate deliveryDate = LocalDate.of(2023, 12, 1); // (12/2023)

    public Airplane(String manufacturer, String model, double overallLength, double cabinLength, double wingSpan, double height, double range, String mmo, double maximumTakeoffWeight, String enginePower, double cruisingSpeed, double fuelConsumption, int numBusinessClass, int numPremiumEconomy, int numEconomy, String serialNumber, String id, String carrier, LocalDate manufacturingDate, LocalDate deliveryDate) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.overallLength = overallLength;
        this.cabinLength = cabinLength;
        this.wingSpan = wingSpan;
        this.height = height;
        this.range = range;
        this.mmo = mmo;
        this.maximumTakeoffWeight = maximumTakeoffWeight;
        this.enginePower = enginePower;
        this.cruisingSpeed = cruisingSpeed;
        this.fuelConsumption = fuelConsumption;
        this.numBusinessClass = numBusinessClass;
        this.numPremiumEconomy = numPremiumEconomy;
        this.numEconomy = numEconomy;
        this.serialNumber = serialNumber;
        this.id = id;
        this.carrier = carrier;
        this.manufacturingDate = manufacturingDate;
        this.deliveryDate = deliveryDate;
        body = new Body();
    }
    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public double getOverallLength() {
        return overallLength;
    }

    public double getCabinLength() {
        return cabinLength;
    }

    public double getWingSpan() {
        return wingSpan;
    }

    public double getHeight() {
        return height;
    }

    public double getRange() {
        return range;
    }

    public String getMmo() {
        return mmo;
    }

    public double getMaximumTakeoffWeight() {
        return maximumTakeoffWeight;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public double getCruisingSpeed() {
        return cruisingSpeed;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public int getNumBusinessClass() {
        return numBusinessClass;
    }

    public int getNumPremiumEconomy() {
        return numPremiumEconomy;
    }

    public int getNumEconomy() {
        return numEconomy;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getId() {
        return id;
    }

    public String getCarrier() {
        return carrier;
    }

    @Override
    public String toString() {
        return  "{ main.Airplane : manufacturer = " + manufacturer + ", model = " + model + ", serialNumber = " + serialNumber + ", id = " + id + ", carrier * " + carrier + ", manufacturingDate = " + manufacturingDate + "}";
    }

    public boolean equals(Airplane airplane1, Airplane airplane2) {
        if (airplane1 == null || airplane2 == null) {
            return false;
        }

        if (airplane1 == airplane2) {
            return true;
        }

        if (!(airplane1 instanceof Airplane && airplane2 instanceof Airplane)) {
            return false;
        }

        if (airplane1.getClass() != airplane2.getClass()) {
            return false;
        }

        return airplane1.getSerialNumber().equals(airplane2.getSerialNumber());
    }

    public void addWings(Wing wing01, Wing wing02) {
        body.addLeftWing(wing01);
        body.addRightWing(wing02);
    }

}




