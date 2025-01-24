package facade;

import com.google.common.eventbus.AsyncEventBus;
import enums.PaymentType;
import events.*;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SBFacade implements ISBFacade {
    private final AsyncEventBus eventBus;

    @Inject
    public SBFacade(AsyncEventBus eventBus) {
        this.eventBus = eventBus;
        log.info("PAFacade initialized and registered with the EventBus");
    }

    @Override
    public void scanItem(String barcode, int quantity) {
        log.info("Facade received a scan request for item with barcode {}", barcode);
        eventBus.post(new ScanEvent(barcode, quantity));
    }

    @Override
    public void processPayment(PaymentType paymentType) {
        log.info("Facade received a payment request: {}", paymentType);
        eventBus.post(new PaymentEvent(paymentType));
    }
}
