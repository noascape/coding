package services.payment;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.name.Named;
import enums.PaymentType;
import events.PaymentEvent;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentService {
    protected final AsyncEventBus eventBus;
    private final IPaymentService cardPaymentService;
    private final IPaymentService cashPaymentService;
    private final IPaymentService mobilePaymentService;

    @Inject
    public PaymentService(AsyncEventBus eventBus,
                          @Named("CARD_PAYMENT") IPaymentService cardPaymentService,
                          @Named("CASH_PAYMENT") IPaymentService cashPaymentService,
                          @Named("MOBILE_PAYMENT") IPaymentService mobilePaymentService) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
        this.cardPaymentService = cardPaymentService;
        this.cashPaymentService = cashPaymentService;
        this.mobilePaymentService = mobilePaymentService;
    }

    @Subscribe
    public void handlePayment(PaymentEvent event) {
        log.info("Processing payment: Type= {}", event.getPaymentType());
        float price = 10f;
        switch(event.getPaymentType()) {
            case PaymentType.CARD_PAYMENT:
                cardPaymentService.process(price);
                break;
            case PaymentType.CASH_PAYMENT:
                cashPaymentService.process(price);
                break;
            case PaymentType.MOBILE_PAYMENT:
                mobilePaymentService.process(price);
                break;
        }

        //eventBus.post(new PaymentCompletedEvent());
    }
}
