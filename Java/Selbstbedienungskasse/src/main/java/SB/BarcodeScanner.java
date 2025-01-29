package SB;

import customer.*;
import database.DatabaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import services.scan.IScanService;

@Slf4j
@AllArgsConstructor
public class BarcodeScanner {
    private final IScanService scanService;
    private final Display display;
    private final DatabaseService databaseService;
    private final SB sb;
    private boolean isActive = false;

    public BarcodeScanner(IScanService scanService, DatabaseService databaseService, SB sb) {
        this.scanService = scanService;
        this.display = new Display();
        this.databaseService = databaseService;
        this.sb = sb;
    }

    public boolean scan(ShoppingCartItem item, IDCard id) {
        if (!isActive) {
            sb.start();
        }
        Item dbItem = databaseService.getItemByBarcode(item.getBarcode());
        StringBuilder displayMessage = new StringBuilder();
        displayMessage.append("Name: ").append(dbItem.getName()).append(" | Price: ").append(dbItem.getPrice()).append("€").append(" | Weight: ").append(dbItem.getWeight()).append("kg");
        if (dbItem.isDiscountActive()) {
            displayMessage.append(" | Discount active: ").append(dbItem.getDiscount() * 100).append("% until ").append(dbItem.getDiscountEnd());
        }
        if (dbItem.getAgeRestriction() > 0) {
            displayMessage.append(" | AgeRestriction: ").append(dbItem.getAgeRestriction());
        }
        display.show(displayMessage.toString());
        boolean scanSuccess = scanService.scanItem(item.getBarcode(), item.getQuantity(), item.getWeight(), id.getAge());
        if (scanSuccess) {
            display.show("Please place item on the WeightPlatform!");
        } else {
            display.show("You are not old enough to buy this item. Please return the item to its original location!");
        }
        return scanSuccess;
    }

    public void activate() {
        log.info("Barcode Scanner activated");
        isActive = true;
    }
}
