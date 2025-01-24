package facade;

import enums.PaymentType;

public interface ISBFacade {
    void scanItem(String barcode, int quantity);
    void processPayment(PaymentType paymentType);
}
