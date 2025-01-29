package SB;

import customer.Item;
import customer.ShoppingCartItem;
import customer.WeightBasedItem;
import database.DatabaseService;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WeightPlatform {
    @Getter
    private final List<ShoppingCartItem> placedItems = new ArrayList<>();
    private final DatabaseService databaseService;
    private boolean isCalibrated = false;

    @Inject
    public WeightPlatform(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void calibrate() {
        log.info("WeightPlatform calibrated");
        isCalibrated = true;
    }

    public boolean addItem(ShoppingCartItem item) {
        Item dbItem = databaseService.getItemByBarcode(item.getBarcode());
        if (!isCalibrated) {
            log.warn("WeightPlatform.addItem(...) but is NOT calibrated!");
            return false;
        }
        if (dbItem instanceof WeightBasedItem) {
            log.info("Weight-based item '{}' weighs {} kg (no strict check).", dbItem.getName(), item.getWeight());
            placedItems.add(item);
            return true;
        } else {
            float expectedWeight = dbItem.getWeight() * item.getQuantity();
            float actualWeight = item.getWeight();
            if (Math.abs(expectedWeight - actualWeight) < 0.05f ) {
                log.info("Weight verified for item: {} (expected ~{}kg, actual ~{}kg)",
                        dbItem.getName(), expectedWeight, actualWeight);
                placedItems.add(item);
                return true;
            } else {
                log.warn("Weight mismatch for item: {} (expected ~{}kg, got ~{}kg)",
                        dbItem.getName(), expectedWeight, actualWeight);
                return false;
            }
        }
    }

    public void reset() {
        placedItems.clear();
    }
}


