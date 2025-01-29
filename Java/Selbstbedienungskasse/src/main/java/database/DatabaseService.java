package database;

import customer.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseService {
    private final List<Item> items;

    public DatabaseService() {
        this.items = new ArrayList<>();
        String filePath = "../Selbstbedienungskasse/src/main/resources/items.csv";
        loadItems(filePath);
    }

    private void loadItems(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                String name = parts[0];
                String barcode = parts[1];
                float price = Float.parseFloat(parts[2]);
                float weight = Float.parseFloat(parts[3]);
                String type = parts[4];
                float discount = Float.parseFloat(parts[5]);
                int ageRestriction = Integer.parseInt(parts[6]);

                LocalDateTime discountEnd = null;
                if (parts.length >= 8 && !parts[7].isBlank()) {
                    discountEnd = LocalDateTime.parse(parts[7]);
                }

                Item item = null;
                switch (type) {
                    case "NORMAL" -> item = new NormalItem(name, barcode, price, weight, discount, ageRestriction, discountEnd, 1);
                    case "WEIGHT_BASED" -> item = new WeightBasedItem(name, barcode, price, weight, discount, ageRestriction, discountEnd, 1);
                    case "DISCOUNTED" -> item = new DiscountedItem(name, barcode, price, weight, discount, ageRestriction, discountEnd, 1);
                    case "AGE_RESTRICTED" -> item = new AgeRestrictedItem(name, barcode, price, weight, discount, ageRestriction, discountEnd, 1);
                }
                items.add(item);

            }
        } catch (IOException e) {log.error("Could not read file: {}", filePath, e);}
    }

    public Item getItemByBarcode(String barcode) {
        return items.stream()
                .filter(item -> item.getBarcode().equals(barcode))
                .findFirst()
                .orElse(null);
    }
}



