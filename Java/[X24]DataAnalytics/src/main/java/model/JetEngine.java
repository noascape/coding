package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class JetEngine {
    private String id;
    private Manufacturer manufacturer;
    private int thrust;
    private double fuelConsumption;
    private LocalDateTime lastMaintenance;
    private double operationalHours;
    private double temperature;
    private double vibrationLevel;
    private EngineState engineState;
    private boolean status;
}