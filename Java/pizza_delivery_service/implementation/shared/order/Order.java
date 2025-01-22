package shared.order;

import dp.visitor.ICartItem;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class Order {
    private final List<ICartItem> items;
    private final double totalCost;

    public Order(List<ICartItem> items, double totalCost) {
        this.items = Collections.unmodifiableList(items);
        this.totalCost = totalCost;
    }
}