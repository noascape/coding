package model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public enum Configuration {
    INSTANCE;

    public List<JetEngine> generateTestData() {
        return Arrays.asList(
                new JetEngine("E01", Manufacturer.GE, 150, 2500, LocalDateTime.now().minusDays(30), 5000, 850, 1.5, EngineState.ACTIVE, true),
                new JetEngine("E02", Manufacturer.ROLLS_ROYCE, 200, 2600, LocalDateTime.now().minusDays(50), 6000, 920, 1.8, EngineState.MAINTENANCE, true),
                new JetEngine("E03", Manufacturer.PRATT_WHITNEY, 180, 2400, LocalDateTime.now().minusDays(10), 5500, 880, 2.0, EngineState.INACTIVE, false),
                new JetEngine("E04", Manufacturer.SAFRAN, 170, 2300, LocalDateTime.now().minusDays(90), 5800, 810, 1.6, EngineState.ACTIVE, true),
                new JetEngine("E05", Manufacturer.GE, 160, 2700, LocalDateTime.now().minusDays(15), 4700, 860, 1.7, EngineState.MAINTENANCE, true),
                new JetEngine("E06", Manufacturer.ROLLS_ROYCE, 190, 2800, LocalDateTime.now().minusDays(25), 6100, 900, 1.9, EngineState.ACTIVE, true),
                new JetEngine("E07", Manufacturer.PRATT_WHITNEY, 210, 2900, LocalDateTime.now().minusDays(70), 6400, 930, 2.1, EngineState.FAILED, false),
                new JetEngine("E08", Manufacturer.SAFRAN, 170, 2450, LocalDateTime.now().minusDays(85), 5300, 840, 1.6, EngineState.INACTIVE, false),
                new JetEngine("E09", Manufacturer.GE, 140, 2650, LocalDateTime.now().minusDays(5), 4600, 830, 1.3, EngineState.ACTIVE, true),
                new JetEngine("E10", Manufacturer.ROLLS_ROYCE, 220, 3000, LocalDateTime.now().minusDays(60), 6200, 910, 2.2, EngineState.ACTIVE, true),
                new JetEngine("E11", Manufacturer.PRATT_WHITNEY, 180, 2500, LocalDateTime.now().minusDays(40), 5400, 880, 2.0, EngineState.MAINTENANCE, true),
                new JetEngine("E12", Manufacturer.SAFRAN, 200, 2800, LocalDateTime.now().minusDays(45), 5700, 860, 1.9, EngineState.FAILED, false),
                new JetEngine("E13", Manufacturer.GE, 170, 2400, LocalDateTime.now().minusDays(55), 4800, 850, 1.5, EngineState.ACTIVE, true),
                new JetEngine("E14", Manufacturer.ROLLS_ROYCE, 210, 2750, LocalDateTime.now().minusDays(20), 6600, 920, 1.8, EngineState.MAINTENANCE, true),
                new JetEngine("E15", Manufacturer.PRATT_WHITNEY, 190, 2700, LocalDateTime.now().minusDays(12), 5000, 870, 1.7, EngineState.INACTIVE, false)
        );
    }
}