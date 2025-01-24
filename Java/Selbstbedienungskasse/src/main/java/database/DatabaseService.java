package database;

import customer.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseService {
    private final List<Item> items;
    // Passen Sie den Pfad ggf. an Ihr Projekt an:
    private final String filePath = "C:/Users/Noah/Documents/GitHub/coding/Java/Selbstbedienungskasse/src/main/resources/items.csv";

    public DatabaseService() {
        this.items = new ArrayList<>();
        loadItems(filePath);
    }

    private void loadItems(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Erste Zeile (Kopfzeile) überspringen
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                // BOM entfernen & trimmen
                line = line.replace("\uFEFF", "").trim();

                // Überspringen, wenn die Zeile leer ist
                if (line.isEmpty()) {
                    continue;
                }

                // Spalten aufteilen
                String[] parts = line.split(";");
                // Überspringen, wenn nicht genug Spalten vorhanden
                if (parts.length < 7) {
                    log.warn("Skipping invalid CSV line: {}", line);
                    continue;
                }

                // Felder parsen
                String name = parts[0];
                String barcode = parts[1];
                float price = Float.parseFloat(parts[2]);
                float weight = Float.parseFloat(parts[3]);
                String type = parts[4];
                float discount = Float.parseFloat(parts[5]);
                int ageRestriction = Integer.parseInt(parts[6]);

                // Erzeugen des passenden Item-Objekts
                switch (type) {
                    case "NORMAL" -> items.add(new NormalItem(name, barcode, price, weight, ageRestriction));
                    case "WEIGHT_BASED" -> items.add(new WeightBasedItem(name, barcode, price, weight));
                    case "DISCOUNTED" -> items.add(new DiscountedItem(name, barcode, price, weight, discount));
                    case "AGE_RESTRICTED" -> items.add(new AgeRestrictedItem(name, barcode, price, weight, ageRestriction));
                    default -> log.warn("Unknown item type: {}", type);
                }
            }
        } catch (IOException e) {
            log.error("Could not read file: {}", filePath, e);
        } catch (NumberFormatException e) {
            log.error("Could not parse numeric values in CSV file: {}", filePath, e);
        }
    }

    public Item getItemByBarcode(String barcode) {
        return items.stream()
                .filter(item -> item.getBarcode().equals(barcode))
                .findFirst()
                .orElse(null);
    }
}



