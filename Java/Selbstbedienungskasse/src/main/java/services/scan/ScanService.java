package services.scan;

import SB.WeightPlatform;
import com.google.common.eventbus.AsyncEventBus;
import com.google.inject.Inject;
import customer.Item;
import database.DatabaseService;
import events.ScanEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public class ScanService implements IScanService {

    private final AsyncEventBus eventBus;
    private final WeightPlatform weightPlatform;
    private final DatabaseService databaseService;
    private final List<Item> scannedItems = new ArrayList<>();

    @Inject
    public ScanService(AsyncEventBus eventBus, WeightPlatform weightPlatform, DatabaseService databaseService) {
        this.eventBus = eventBus;
        this.weightPlatform = weightPlatform;
        this.databaseService = databaseService;
        this.eventBus.register(this);
        log.info("ScanService (Mikroservice #1) initialized and registered to EventBus");
    }

    @Override
    public boolean scanItem(String barcode, int quantity, float customWeight, int customerAge) {
        Item dbItem = databaseService.getItemByBarcode(barcode);
        if (dbItem == null) {
            log.warn("ScanService: item not found => barcode={}", barcode);
            return false;
        }

        if (customerAge < dbItem.getAgeRestriction()) {
            log.warn("ScanService: too young => item='{}' ageNeeded={}, but got={}", dbItem.getName(), dbItem.getAgeRestriction(), customerAge);
            return false;
        }
        eventBus.post(new ScanEvent(
                dbItem.getName(),
                dbItem.getPrice(),
                quantity,
                customWeight,
                dbItem.getType(),
                dbItem.getDiscount(),
                dbItem.getDiscountEnd(),
                dbItem.getAgeRestriction()
        ));
        return true;
    }
}




