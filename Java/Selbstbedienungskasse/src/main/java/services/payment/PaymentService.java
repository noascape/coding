package services.payment;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import customer.*;
import enums.PaymentType;
import events.PaymentEvent;
import events.ScanEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import visitor.PricingVisitor;

@Slf4j
public class PaymentService {

    private final AsyncEventBus eventBus;
    private final PaymentServiceFactory factory;
    @Getter
    private float totalSum = 0f;

    @Inject
    public PaymentService(AsyncEventBus eventBus, PaymentServiceFactory factory) {
        this.eventBus = eventBus;
        this.factory = factory;
        eventBus.register(this);
        log.info("PaymentService (Mikroservice #2) initialized and registered to EventBus");
    }

    @Subscribe
    public void handleScanEvent(ScanEvent event) {
        Item item = createVisitorItem(event);
        PricingVisitor visitor = new PricingVisitor();
        item.accept(visitor);

        float linePrice = visitor.getTotalPrice();
        totalSum += linePrice;

        log.info("PaymentService: handleScanEvent => item='{}', linePrice={}, totalSum={}", event.getName(), linePrice, totalSum);
    }

    @Subscribe
    public void handlePaymentEvent(PaymentEvent event) {
        log.info("PaymentService: PaymentEvent => type={}, totalSum={}", event.getPaymentType(), totalSum);
        initiatePayment(event.getPaymentType());
    }

    private void initiatePayment(PaymentType type) {
        IPaymentService impl = factory.create(type);
        impl.process(totalSum);
        totalSum = 0f;
        log.info("PaymentService: Payment done => totalSum reset=0");
    }

    private Item createVisitorItem(ScanEvent ev) {
        float usedWeight = switch (ev.getItemType()) {
            case WEIGHT_BASED -> ev.getCustomWeight();
            default -> ev.getQuantity();
        };
        return switch (ev.getItemType()) {
            case NORMAL -> new NormalItem(ev.getName(), ev.getName(), ev.getBasePrice(), usedWeight, ev.getDiscount(), ev.getAgeRestriction(), ev.getDiscountEnd(), ev.getQuantity());
            case DISCOUNTED -> new DiscountedItem(ev.getName(), ev.getName(), ev.getBasePrice(), usedWeight, ev.getDiscount(), ev.getAgeRestriction(), ev.getDiscountEnd(), ev.getQuantity());
            case WEIGHT_BASED -> new WeightBasedItem(ev.getName(), ev.getName(), ev.getBasePrice(), usedWeight, ev.getDiscount(), ev.getAgeRestriction(), ev.getDiscountEnd(), ev.getQuantity());
            case AGE_RESTRICTED -> new AgeRestrictedItem(ev.getName(), ev.getName(), ev.getBasePrice(), usedWeight, ev.getDiscount(), ev.getAgeRestriction(), ev.getDiscountEnd(), ev.getQuantity());
            default -> {
                log.warn("Unknown itemType='{}', fallback Normal", ev.getItemType());
                yield new NormalItem(ev.getName(), ev.getName(), ev.getBasePrice(), usedWeight, ev.getDiscount(), ev.getAgeRestriction(), ev.getDiscountEnd(), ev.getQuantity());
            }
        };
    }
}




