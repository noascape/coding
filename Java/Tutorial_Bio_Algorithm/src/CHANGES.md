# CHANGES.md – Lion Optimization Algorithm (LOA)

## KI-Tool-Nutzung

- Tool: ChatGPT
- Nutzung: Analyse der Basisimplementierung, Ableitung von Korrekturen, Refactoring, Testergänzung, Logging-Konzept und JavaDoc-Formulierungen.
- Prüfung: Die übernommenen Änderungen wurden auf Kompilierbarkeit der Hauptklassen geprüft. Die Tests sind für JUnit 5 und AssertJ ausgelegt und wurden erfolgreich ausgeführt.

## Aufgabe 1 – Analyse und Korrektur der Implementierung

Identifizierte Probleme und Korrekturen:

### 1. Abhängigkeit von Lombok entfernt

- Problem: Die Basisimplementierung nutzte Lombok-Anmerkungen wie `@Data` oder `@Slf4j`. Dadurch war die Abgabe ohne zusätzliche Projektkonfiguration schwerer nachvollziehbar und nicht vollständig auf Standard-Java beschränkt.
- Korrektur: Getter und Zustandsmethoden wurden explizit implementiert. Logging erfolgt über eine eigene Logging-Komponente.

### 2. Personal Best ergänzt

- Problem: Für die Beobachtung des lokalen Gedächtnisses wird pro Agent die beste bisher gefundene Position benötigt. Die Basisimplementierung speicherte überwiegend aktuelle Fitnesswerte sowie globale oder pride-bezogene Bestwerte.
- Korrektur: Jeder `Lion` besitzt `personalBestPosition` und `personalBestFitness`. Diese Werte werden bei akzeptierten Verbesserungen aktualisiert.

### 3. Agenten-IDs ergänzt

- Problem: Positionsupdates waren ohne stabile Agentenkennung im Logfile nicht eindeutig nachvollziehbar.
- Korrektur: Jeder Löwe erhält eine eindeutige ID. Diese wird im CSV-Log verwendet.

### 4. Bounds-Behandlung vereinheitlicht

- Problem: Positionsupdates müssen unabhängig vom jeweiligen Operator im definierten Suchraum bleiben.
- Korrektur: Alle Bewegungsoperatoren verwenden eine zentrale `clamp`-Methode.

### 5. Initialisierung robuster gemacht

- Problem: Kleine Populationen oder ungünstige Parameter konnten zu leeren Prides oder fehlenden Männchen führen.
- Korrektur: Die Konfiguration validiert `populationSize >= numberOfPrides`. Bei der Initialisierung erhält jede Pride mindestens einen Löwen und genau ein Männchen.

### 6. LOA-Phasen klarer getrennt

- Problem: Die algorithmischen Phasen waren in der ursprünglichen Ablaufstruktur schwerer voneinander zu unterscheiden.
- Korrektur: Jagd, Roaming, Verteidigung, Paarung, Jungenwachstum, Nomadenbewegung, Übernahme, Migration und Populationskontrolle wurden als eigene Methoden strukturiert.

### 7. Greedy-Akzeptanz vereinheitlicht

- Problem: Die Annahme neuer Positionen und die Aktualisierung des lokalen Gedächtnisses sollten konsistent an einer zentralen Stelle erfolgen.
- Korrektur: Die Methode `tryImprove(...)` bündelt die Annahmeregel. Neue Positionen werden nur akzeptiert, wenn sie die Fitness verbessern. Anschließend wird der Personal Best aktualisiert.

## Aufgabe 2 – Analyse und Verbesserung des Testszenarios

Ergänzt bzw. verbessert wurden Tests für:

- ungültige Konfigurationen,
- unpassende Suchraumgrenzen,
- Initialisierung mit genau einem Männchen pro Pride,
- vollständige Populationsinitialisierung,
- konstante Populationsgröße nach der Optimierung,
- Einhaltung der Suchraumgrenzen,
- monotone Verbesserung oder Beibehaltung des Global Best,
- Reproduzierbarkeit über Seeds,
- kleine Randfall-Populationen,
- strukturiertes CSV-Logging auf der Ackley-Funktion.

Die Tests verwenden JUnit 5 und AssertJ.

## Aufgabe 3 – Clean Coding und SOLID

Refactoring-Maßnahmen:

### Single Responsibility Principle

- `AckleyFunction` kapselt ausschließlich die Testfunktion.
- `AlgorithmLogger` kapselt das CSV-Logging.
- `LoaConfig` kapselt die Parametrisierung und Validierung eines Algorithmuslaufs.
- `Lion`, `Pride`, `Cub` und `PopulationStats` wurden in Modellklassen ausgelagert.
- `Gender` und `Role` wurden als eigene Typen ausgelagert.
- `LionOptimizationAlgorithm` konzentriert sich auf die Optimierungslogik.

### Dependency Inversion Principle

- Der Algorithmus hängt von der Abstraktion `ObjectiveFunction` ab und nicht von einer konkreten Testfunktion.
- Dadurch können andere Zielfunktionen ergänzt werden, ohne die Algorithmusklasse zu verändern.

### Open/Closed Principle

- Neue Zielfunktionen können durch Implementierung von `ObjectiveFunction` hinzugefügt werden.
- Die bestehende Algorithmuslogik muss dafür nicht angepasst werden.

### Clean Code

- sprechende Methodennamen,
- zentrale Konstanten,
- keine Lombok-Magie,
- keine Build-Artefakte im Quellcode,
- klare Konfiguration über `LoaConfig`,
- Auslagerung von Modell- und Typklassen.

### KISS/DRY

- Wiederholte Bewegungs- und Logikbestandteile wurden in `moveAndLog(...)`, `tryImprove(...)` und `clamp(...)` gebündelt.
- Validierungslogik für Konfigurationsparameter wurde zentral in `LoaConfig` umgesetzt.

## Aufgabe 4 – Logging Ackley-Funktion

Implementiert:

- Logging-Komponente: `src/main/java/edu/swarmintelligence/loa/AlgorithmLogger.java`
- Beispielausgabe: `src/resources/algorithm_run.log`
- Demo-Klasse: `src/main/java/edu/swarmintelligence/loa/LoaDemo.java`

Das Logfile ist CSV-basiert und enthält:

```text
iteration;agentId;positionBefore;positionAfter;personalBest;personalBestFitness;globalBest;globalBestFitness;popAvgFitness;popStdDev;distToOptimum