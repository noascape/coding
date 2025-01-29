package customer;

import lombok.Data;

@Data
public class ShoppingCartItem {
    private final String name;
    private final String barcode;
    private final int quantity;
    private final float weight;
}

