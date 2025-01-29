package SB;

import com.google.common.eventbus.AsyncEventBus;
import database.DatabaseService;
import enums.State;
import jakarta.inject.Inject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import events.*;
import services.scan.IScanService;

@Slf4j
@Data
public class SB {
    private final AsyncEventBus eventBus;
    public final BarcodeScanner barcodeScanner;
    public final WeightPlatform weightPlatform;
    public final Touchscreen touchscreen;
    public final Display display;
    private final IScanService scanService;
    private State state = State.INACTIVE;

    @Inject
    public SB(AsyncEventBus eventBus, IScanService scanService, DatabaseService databaseService) {
        this.scanService = scanService;
        this.barcodeScanner = new BarcodeScanner(scanService, databaseService, this);
        this.weightPlatform = new WeightPlatform(databaseService);
        this.touchscreen = new Touchscreen(this);
        this.display = new Display();
        this.eventBus = eventBus;
    }

    public void start() {
        if (this.state == State.INACTIVE) {
            barcodeScanner.activate();
            weightPlatform.calibrate();
            display.show("Checkout ready. Please scan or lay down the Item.");
            this.state = State.ACTIVE;
        }
    }

    public void postPaymentEvent(PaymentEvent event) {
        log.info("SB: posting PaymentEvent => {}", event);
        eventBus.post(event);
        weightPlatform.reset();
    }
}

