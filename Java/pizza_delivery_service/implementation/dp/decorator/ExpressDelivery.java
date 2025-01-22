package dp.decorator;

import shared.order.Order;

public class ExpressDelivery extends DeliveryDecorator {
    public ExpressDelivery(IDelivery decoratedDelivery) {
        super(decoratedDelivery);
    }

    public double getTotalIncludingDelivery(Order order) {
        return super.calculateCost(order) + DeliveryType.EXPRESS.getCost();
    }

    public String getDescription() {
        return super.getDescription() + ", express";
    }
}