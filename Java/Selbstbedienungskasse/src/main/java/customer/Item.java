package customer;

import lombok.Data;
import visitor.Visitable;

@Data
public abstract class Item implements Visitable {
    private final String itemName;
    private final String barcode;
    private final float price;
    private final float weight;
    private final int ageRestriction;

    public Item(String itemName, String barcode, float price, float weight, int ageRestriction) {
        this.itemName = itemName;
        this.barcode = barcode;
        this.price = price;
        this.weight = weight;
        this.ageRestriction = ageRestriction;
    }
}

