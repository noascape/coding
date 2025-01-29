package customer;

import enums.ItemType;
import visitor.IVisitor;
import java.time.LocalDateTime;

public class WeightBasedItem extends Item {
    public WeightBasedItem(String name, String barcode, float price, float weight, float discount, int ageRestriction, LocalDateTime discountEnd, int quantity) {
        super(name, barcode, price, weight, discount, ageRestriction, discountEnd, quantity);
        this.type = ItemType.WEIGHT_BASED;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

