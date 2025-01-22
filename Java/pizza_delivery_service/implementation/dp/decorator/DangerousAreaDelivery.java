package dp.decorator;

import shared.order.Order;

public class DangerousAreaDelivery extends DeliveryDecorator {
    public DangerousAreaDelivery(IDelivery decoratedDelivery) {
        super(decoratedDelivery);
    }

    public double getTotalIncludingDelivery(Order order) {
        return super.calculateCost(order) + DeliveryType.DANGEROUS_AREA.getCost();
    }

    public String getDescription() {
        return super.getDescription() + ", dangerous area delivery";
    }
}