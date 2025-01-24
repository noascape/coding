package de.project.bridge;

public enum AircraftType {
    AIRBUS_A350("Airbus A350"),
    BOEING_787("Boeing 787");

    private final String name;

    AircraftType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
