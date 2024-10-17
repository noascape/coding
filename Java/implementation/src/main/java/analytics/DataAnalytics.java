package analytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataAnalytics implements IDataAnalytics {
    private static final Logger logger = LoggerFactory.getLogger(DataAnalytics.class);

    public void execute01() {
        logger.debug("--- Summiere die Gesamtlaufzeit aller Triebwerke ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute02() {
        logger.debug("--- Berechne die maximale Temperatur unter den Triebwerken ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute03() {
        logger.debug("--- Berechne den Durchschnitt des Kraftstoffverbrauchs aktiver Triebwerke ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute04() {
        logger.debug("--- Extrahiere alle Hersteller von Triebwerken ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute05() {
        logger.debug("--- Extrahiere alle Hersteller von Triebwerken (ohne Duplikate) ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute06() {
        logger.debug("--- Gebe zusammenfassende Statistik für die Schubkraft (thrust) aller Triebwerke aus ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute07() {
        logger.debug("--- Zähle Triebwerke in Wartung ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute08() {
        logger.debug("--- Filtere aktive Triebwerke ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute09() {
        logger.debug("--- Filtere Triebwerke mit einem Schub größer als 200 kN ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute10() {
        logger.debug("--- Extrahiere die IDs von Triebwerken, die defekt sind (Status: false) ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute11() {
        logger.debug("--- Sortiere Triebwerke nach Schub aufsteigend ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute12() {
        logger.debug("--- Sortiere die Triebwerke nach Schub in aufsteigender Reihenfolge und dann nach Kraftstoffverbrauch in absteigender Reihenfolge ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute13() {
        logger.debug("--- Filtere aktive Triebwerke und sortiere sie nach Schub (aufsteigend) und Kraftstoffverbrauch (absteigend) ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute14() {
        logger.debug("--- Partioniere Triebwerke in solche, die gewartet werden müssen, und solche, die nicht gewartet werden müssen. ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute15() {
        logger.debug("--- Partitioniere Triebwerke, die in den letzten 30 Tagen gewartet wurden, und sortiere sie nach Temperatur (aufsteigend) ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute16() {
        logger.debug("--- Gruppiere Triebwerke nach Hersteller ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute17() {
        logger.debug("--- Gruppiere Triebwerke nach ihrem Zustand und filtere nach Schubkraft über 150 kN ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute18() {
        logger.debug("--- Filtere Triebwerke mit Schubkraft über 160 kN und unter 230 kN, die aktiv oder in Wartung sind, und gruppiere nach Hersteller ---");
        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);
        logger.debug("---");
    }

    public void execute19() {
        logger.debug("--- Gruppiere nicht defekte Triebwerke pro Hersteller ohne SAFRAN mit Schub zwischen 125 und 200 kN ");
        logger.debug("    und berechne je Gruppe (Manufacturer) den durschnittlichen Kerosinverbrauch ---");

        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);

        logger.debug("---");
    }

    // Kombination von tempResult und result
    public void execute20() {
        logger.debug("--- Zähle Triebwerke pro Hersteller, die einen Schub größer als 170 kN haben und nicht defekt sind, ");
        logger.debug("    sortiere die Hersteller nach Anzahl der Triebwerke absteigend ---");

        // [return datatype] result = [lambda/stream] here.
        // logger.debug(result);

        logger.debug("---");
    }
}