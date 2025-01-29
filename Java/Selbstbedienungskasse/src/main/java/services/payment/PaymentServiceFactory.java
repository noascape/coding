package services.payment;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import enums.PaymentType;

public class PaymentServiceFactory {

    private final IPaymentService cardPaymentService;
    private final IPaymentService cashPaymentService;
    private final IPaymentService mobilePaymentService;

    @Inject
    public PaymentServiceFactory(
            @Named("CARD_PAYMENT") IPaymentService cardPaymentService,
            @Named("CASH_PAYMENT") IPaymentService cashPaymentService,
            @Named("MOBILE_PAYMENT") IPaymentService mobilePaymentService
    ) {
        this.cardPaymentService = cardPaymentService;
        this.cashPaymentService = cashPaymentService;
        this.mobilePaymentService = mobilePaymentService;
    }

    public IPaymentService create(PaymentType type) {
        return switch(type) {
            case CARD_PAYMENT   -> cardPaymentService;
            case CASH_PAYMENT   -> cashPaymentService;
            case MOBILE_PAYMENT -> mobilePaymentService;
        };
    }
}

