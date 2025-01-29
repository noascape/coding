package customer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ShoppingCart {
    private final List<ShoppingCartItem> items = new ArrayList<>();

    public void addItem(ShoppingCartItem item) {
        items.add(item);
    }

}

