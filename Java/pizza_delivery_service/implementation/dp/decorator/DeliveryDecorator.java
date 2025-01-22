package dp.decorator;

import lombok.Getter;
import lombok.ToString;
import shared.order.Order;

@Getter
@ToString
public abstract class DeliveryDecorator implements IDelivery {
    protected final IDelivery wrappedDelivery;

    public DeliveryDecorator(IDelivery delivery) {
        this.wrappedDelivery = delivery;
    }

    public double calculateCost(Order order) {
        return wrappedDelivery.getTotalIncludingDelivery(order);
    }

    public String getDescription() {
        return wrappedDelivery.getDescription();
    }
}