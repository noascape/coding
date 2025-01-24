package SB.weightPlatform;

import customer.Item;
import customer.WeightBasedItem;
import database.DatabaseService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WeightPlatform {
    private final List<Item> scannedItems = new ArrayList<>();
    private final DatabaseService databaseService;

    public WeightPlatform(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void calibrate() {
        log.info("WeightPlatform calibrated");
    }

    /**
     * Legt ein Item auf die Plattform und verifiziert das Gewicht,
     * falls es sich um ein WeightBasedItem handelt.
     */
    public void addItem(Item item, float actualWeight) {
        if (item instanceof WeightBasedItem) {
            // Nur für WeightBasedItems Gewicht prüfen
            if (Math.abs(item.getWeight() - actualWeight) < 0.05f) {
                log.info("Weight verified for item: {}", item.getItemName());
                scannedItems.add(item);
            } else {
                log.warn("Weight mismatch for item: {} (expected={}kg, actual={}kg)",
                        item.getItemName(), item.getWeight(), actualWeight);
            }
        } else {
            // Für normale Items einfach hinzufügen
            scannedItems.add(item);
        }
    }

    public float calculateTotalWeight() {
        float totalWeight = 0f;
        for (Item item : scannedItems) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    public float calculateTotalPrice() {
        float totalPrice = 0f;
        for (Item item : scannedItems) {
            if (item instanceof WeightBasedItem) {
                // Preis = Grundpreis * Gewicht
                totalPrice += item.getPrice() * item.getWeight();
            } else {
                totalPrice += item.getPrice();
            }
        }
        return totalPrice;
    }
}


