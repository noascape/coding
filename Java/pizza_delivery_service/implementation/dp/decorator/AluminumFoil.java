package dp.decorator;

import shared.order.Order;

public class AluminumFoil extends DeliveryDecorator {
    public AluminumFoil(IDelivery decoratedDelivery) {
        super(decoratedDelivery);
    }

    public double getTotalIncludingDelivery(Order order) {
        return super.calculateCost(order) + DeliveryType.ALUMINUM_FOIL.getCost();
    }

    public String getDescription() {
        return super.getDescription() + ", aluminum foil";
    }
}