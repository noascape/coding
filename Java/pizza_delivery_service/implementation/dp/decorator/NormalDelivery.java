package dp.decorator;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import shared.order.Order;

@Slf4j
@Getter
@ToString
public class NormalDelivery implements IDelivery {
    public double getTotalIncludingDelivery(Order order) {
        return order.getTotalCost() + DeliveryType.NORMAL.getCost();
    }

    public String getDescription() {
        return "normal delivery";
    }
}