package dp.decorator;

import lombok.extern.slf4j.Slf4j;
import shared.order.Order;

@Slf4j
public class NightDelivery extends DeliveryDecorator {
    public NightDelivery(IDelivery decoratedDelivery) {
        super(decoratedDelivery);
    }

    public double getTotalIncludingDelivery(Order order) {
        return super.calculateCost(order) + DeliveryType.NIGHT_DELIVERY.getCost();
    }

    public String getDescription() {
        return super.getDescription() + ", night delivery";
    }
}