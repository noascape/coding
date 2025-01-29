package customer;

import enums.ItemType;
import visitor.IVisitor;
import java.time.LocalDateTime;

public class DiscountedItem extends Item {
    public DiscountedItem(String name, String barcode, float price, float weight, float discount, int ageRestriction, LocalDateTime discountEnd, int quantity) {
        super(name, barcode, price, weight, discount, ageRestriction, discountEnd, quantity);
        this.type = ItemType.DISCOUNTED;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

