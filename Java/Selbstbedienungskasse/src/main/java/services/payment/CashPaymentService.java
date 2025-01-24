package services.payment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CashPaymentService implements IPaymentService {
    public void process(float price) {
        log.info("Process Cash Payment: {} €", price);
    }
}
