package customer;

import enums.PaymentType;
import lombok.AllArgsConstructor;
import SB.SB;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Customer {
    private final IDCard idCard;
    private final ShoppingCart cart;
    private final SB sb;

    public void touch(SB sb) {
        sb.touchscreen.touch();
    }

    public void scanItems(SB sb) {
        log.info("Customer starts scanning his items.");
        for (ShoppingCartItem item : cart.getItems()) {
            boolean scanSuccess = sb.barcodeScanner.scan(item, idCard);
            if (scanSuccess) {moveItem(item);}
        }
    }

    public void moveItem(ShoppingCartItem item) {
        log.info("Customer lays item: {} on the Weight platform", item.getName());
        boolean verified = sb.weightPlatform.addItem(item);
        if (!verified) {
            sb.display.show("Weight Mismatch. Calling staff assistance.");
        }
    }

    public void pay(PaymentType paymentType) {
        log.info("Customer chooses to pay via {}", paymentType);
        sb.postPaymentEvent(new events.PaymentEvent(paymentType));
    }
}
