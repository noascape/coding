package dp.decorator;

import shared.order.Order;

public class HeatingBox extends DeliveryDecorator {
    public HeatingBox(IDelivery decoratedDelivery) {
        super(decoratedDelivery);
    }

    public double getTotalIncludingDelivery(Order order) {
        return super.calculateCost(order) + DeliveryType.HEATING_BOX.getCost();
    }

    public String getDescription() {
        return super.getDescription() + ", heating Box";
    }
}