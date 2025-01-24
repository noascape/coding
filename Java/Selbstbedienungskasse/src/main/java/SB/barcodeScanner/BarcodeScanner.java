package SB.barcodeScanner;

import com.google.common.eventbus.AsyncEventBus;
import customer.Item;
import database.DatabaseService;
import events.ScanEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BarcodeScanner {
    private final AsyncEventBus eventBus;
    private final DatabaseService databaseService;

    public BarcodeScanner(AsyncEventBus eventBus, DatabaseService databaseService) {
        this.eventBus = eventBus;
        this.databaseService = databaseService;
    }


    public void scan(String barcode, int quantity) {
        //hier noch die Datenbank auswerten, um an die anderen Daten zu gelangen denke ich:
        //Item item = databaseService.getItemByBarcode(barcode);
        eventBus.post(new ScanEvent(barcode, quantity));
    }

    public void activate() {
        log.info("Activating Barcode Scanner");
        eventBus.register(this);
    }
}
