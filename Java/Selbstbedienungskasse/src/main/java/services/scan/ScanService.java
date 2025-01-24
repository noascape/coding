package services.scan;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import customer.Item;
import database.DatabaseService;
import events.ScanEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScanService implements IScanService {
    private final AsyncEventBus eventBus;
    private final DatabaseService databaseService;

    @Inject
    public ScanService(AsyncEventBus eventBus, DatabaseService databaseService) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
        log.info("ScanService initialized and registered with the EventBus");
        this.databaseService = databaseService;
    }


    @Override
    @Subscribe
    public void handleScanEvent(ScanEvent event) {
        String barcode = event.getBarcode();
        int quantity = event.getQuantity();
        Item item = databaseService.getItemByBarcode(barcode);
        if (item != null) {
            log.info("Item scanned: {}, Quantity: {}", item.getItemName(), quantity);
            //hier verifizieren des Gewichts noch
            // Hier könntest du z. B. die Artikel + Quantity in einem Service oder Einkaufswagen speichern.
            // Oder im EventBus ein weiteres Event feuern, um PaymentService zu informieren usw.
        } else {
            log.warn("Item not found: {}", barcode);
        }
        //eventBus.post(new PaymentEvent());        //sendet die Scan-Ereignisse an den PaymentService
    }
}
