package customer;

import visitor.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public float calculateTotalPrice(IVisitor visitor) {
        for (Item item : items) {
            item.accept(visitor);
        }
        return ((PricingVisitor) visitor).getTotalPrice();
    }
}

