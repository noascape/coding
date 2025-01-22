package shared.cart;

import dp.visitor.CartVisitor;
import dp.visitor.ICartItem;
import shared.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<ICartItem> items = new ArrayList<>();

    public void addItem(ICartItem item) {
        items.add(item);
    }

    public double calculateTotal(CartVisitor visitor) {
        return items.stream()
                .mapToDouble(item -> item.accept(visitor))
                .sum();
    }

    public Order toOrder(CartVisitor visitor) {
        double totalCost = calculateTotal(visitor);
        return new Order(new ArrayList<>(items), totalCost);
    }
}