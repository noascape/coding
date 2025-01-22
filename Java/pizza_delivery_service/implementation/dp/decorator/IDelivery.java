package dp.decorator;

import shared.order.Order;

public interface IDelivery {
    double getTotalIncludingDelivery(Order order);

    String getDescription();
}