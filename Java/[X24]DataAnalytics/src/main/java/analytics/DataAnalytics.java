package analytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//neu
import model.*;
import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


public class DataAnalytics implements IDataAnalytics {
    private static final Logger logger = LoggerFactory.getLogger(DataAnalytics.class);
    private final List<JetEngine> engines = Configuration.INSTANCE.generateTestData();

    public void execute01() {
        logger.debug("--- Summiere die Gesamtlaufzeit aller Triebwerke ---");
        double totalOperationalHours = engines.stream()
                .mapToDouble(JetEngine::getOperationalHours)
                .sum();
        logger.debug("Total Operational Hours: {}", totalOperationalHours);
        logger.debug("---");
    }

    public void execute02() {
        logger.debug("--- Berechne die maximale Temperatur unter den Triebwerken ---");
        OptionalDouble maxTemperature = engines.stream()
                .mapToDouble(JetEngine::getTemperature)
                .max();
        logger.debug("Max Temperature: {}", maxTemperature.orElse(0));
        logger.debug("---");
    }

    public void execute03() {
        logger.debug("--- Durchschnitt des Kraftstoffverbrauchs aktiver Triebwerke ---");
        OptionalDouble avgFuelConsumption = engines.stream()
                .filter(engine -> engine.getEngineState() == EngineState.ACTIVE)
                .mapToDouble(JetEngine::getFuelConsumption)
                .average();
        logger.debug("Avg Fuel Consumption (active engines): {}", avgFuelConsumption.orElse(0));
        logger.debug("---");
    }

    public void execute04() {
        logger.debug("--- Extrahiere alle Hersteller von Triebwerken ---");
        List<Manufacturer> manufacturers = engines.stream()
                .map(JetEngine::getManufacturer)
                .collect(Collectors.toList());
        logger.debug("Manufacturers: {}", manufacturers);
        logger.debug("---");
    }

    public void execute05() {
        logger.debug("--- Extrahiere alle Hersteller von Triebwerken (ohne Duplikate) ---");
        Set<Manufacturer> uniqueManufacturers = engines.stream()
                .map(JetEngine::getManufacturer)
                .collect(Collectors.toSet());
        logger.debug("Unique Manufacturers: {}", uniqueManufacturers);
        logger.debug("---");
    }

    public void execute06() {
        logger.debug("--- Zusammenfassende Statistik der Schubkraft aller Triebwerke ---");
        DoubleSummaryStatistics stats = engines.stream()
                .mapToDouble(JetEngine::getThrust)
                .summaryStatistics();
        logger.debug("Thrust Stats: {}", stats);
        logger.debug("---");
    }

    public void execute07() {
        logger.debug("--- Zähle Triebwerke in Wartung ---");
        long count = engines.stream()
                .filter(engine -> engine.getEngineState() == EngineState.MAINTENANCE)
                .count();
        logger.debug("Engines in Maintenance: {}", count);
        logger.debug("---");
    }

    public void execute08() {
        logger.debug("--- Filtere aktive Triebwerke ---");
        List<JetEngine> activeEngines = engines.stream()
                .filter(engine -> engine.getEngineState() == EngineState.ACTIVE)
                .collect(Collectors.toList());
        logger.debug("Active Engines: {}", activeEngines);
        logger.debug("---");
    }

    public void execute09() {
        logger.debug("--- Filtere Triebwerke mit einem Schub größer als 200 kN ---");
        List<JetEngine> powerfulEngines = engines.stream()
                .filter(engine -> engine.getThrust() > 200)
                .collect(Collectors.toList());
        logger.debug("Powerful Engines: {}", powerfulEngines);
        logger.debug("---");
    }

    public void execute10() {
        logger.debug("--- Extrahiere die IDs von Triebwerken, die defekt sind (Status: false) ---");
        List<String> result = engines.stream()
                .filter(engine -> !engine.isStatus())
                .map(JetEngine::getId)
                .collect(Collectors.toList());
        logger.debug(result.toString());
        logger.debug("---");
    }

    public void execute11() {
        logger.debug("--- Sortiere Triebwerke nach Schub aufsteigend ---");
        List<JetEngine> result = engines.stream()
                .sorted(Comparator.comparingInt(JetEngine::getThrust))
                .collect(Collectors.toList());
        logger.debug(result.toString());
        logger.debug("---");
    }

    public void execute12() {
        logger.debug("--- Sortiere die Triebwerke nach Schub aufsteigend und dann nach Kraftstoffverbrauch absteigend ---");
        List<JetEngine> result = engines.stream()
                .sorted(Comparator.comparingInt(JetEngine::getThrust)
                        .thenComparing(Comparator.comparingDouble(JetEngine::getFuelConsumption).reversed()))
                .collect(Collectors.toList());
        logger.debug(result.toString());
        logger.debug("---");
    }

    public void execute13() {
        logger.debug("--- Filtere aktive Triebwerke und sortiere sie nach Schub aufsteigend und Kraftstoffverbrauch absteigend ---");
        List<JetEngine> result = engines.stream()
                .filter(JetEngine::isStatus)
                .sorted(Comparator.comparingInt(JetEngine::getThrust)
                        .thenComparing(Comparator.comparingDouble(JetEngine::getFuelConsumption).reversed()))
                .collect(Collectors.toList());
        logger.debug(result.toString());
        logger.debug("---");
    }

    public void execute14() {
        logger.debug("--- Partitioniere Triebwerke in solche, die gewartet werden müssen, und solche, die nicht gewartet werden müssen ---");
        Map<Boolean, List<JetEngine>> result = engines.stream()
                .collect(Collectors.partitioningBy(engine -> engine.getEngineState() == EngineState.MAINTENANCE));
        logger.debug(result.toString());
        logger.debug("---");
    }

    public void execute15() {
        logger.debug("--- Partitioniere Triebwerke, die in den letzten 30 Tagen gewartet wurden, und sortiere sie nach Temperatur aufsteigend ---");
        List<JetEngine> result = engines.stream()
                .filter(engine -> engine.getLastMaintenance().isAfter(LocalDateTime.now().minusDays(30)))
                .sorted(Comparator.comparingDouble(JetEngine::getTemperature))
                .collect(Collectors.toList());
        logger.debug(result.toString());
        logger.debug("---");
    }

    public void execute16() {
        logger.debug("--- Gruppiere Triebwerke nach Hersteller ---");
        Map<Manufacturer, List<JetEngine>> result = engines.stream()
                .collect(Collectors.groupingBy(JetEngine::getManufacturer));
        logger.debug(result.toString());
        logger.debug("---");
    }

    public void execute17() {
        logger.debug("--- Gruppiere Triebwerke nach ihrem Zustand und filtere nach Schubkraft über 150 kN ---");
        Map<EngineState, List<JetEngine>> result = engines.stream()
                .filter(engine -> engine.getThrust() > 150)
                .collect(Collectors.groupingBy(JetEngine::getEngineState));
        logger.debug(result.toString());
        logger.debug("---");
    }

    public void execute18() {
        logger.debug("--- Filtere Triebwerke mit Schubkraft über 160 kN und unter 230 kN, die aktiv oder in Wartung sind, und gruppiere nach Hersteller ---");
        Map<Manufacturer, List<JetEngine>> result = engines.stream()
                .filter(engine -> engine.getThrust() > 160 && engine.getThrust() < 230)
                .filter(engine -> engine.getEngineState() == EngineState.ACTIVE || engine.getEngineState() == EngineState.MAINTENANCE)
                .collect(Collectors.groupingBy(JetEngine::getManufacturer));
        logger.debug(result.toString());
        logger.debug("---");
    }

    public void execute19() {
        logger.debug("--- Gruppiere nicht defekte Triebwerke pro Hersteller ohne SAFRAN mit Schub zwischen 125 und 200 kN ");
        logger.debug("    und berechne den durchschnittlichen Kerosinverbrauch je Gruppe ---");

        Map<Manufacturer, Double> result = engines.stream()
                .filter(engine -> engine.getThrust() >= 125 && engine.getThrust() <= 200)
                .filter(engine -> engine.isStatus() && engine.getManufacturer() != Manufacturer.SAFRAN)
                .collect(Collectors.groupingBy(JetEngine::getManufacturer,
                        Collectors.averagingDouble(JetEngine::getFuelConsumption)));

        logger.debug(result.toString());
        logger.debug("---");
    }

    public void execute20() {
        logger.debug("--- Zähle Triebwerke pro Hersteller mit Schub > 170 kN und nicht defekt ---");
        Map<Manufacturer, Long> engineCount = engines.stream()
                .filter(engine -> engine.getThrust() > 170 && engine.isStatus())
                .collect(Collectors.groupingBy(JetEngine::getManufacturer, Collectors.counting()));
        logger.debug("Engines per Manufacturer: {}", engineCount);
        logger.debug("---");
    }
}