package dp.decorator;

import shared.order.Order;

public class CashPayment extends DeliveryDecorator {
    public CashPayment(IDelivery decoratedDelivery) {
        super(decoratedDelivery);
    }

    public double getTotalIncludingDelivery(Order order) {
        return super.calculateCost(order) + DeliveryType.CASH_PAYMENT.getCost();
    }

    public String getDescription() {
        return super.getDescription() + ", cash payment";
    }
}