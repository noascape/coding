package customer;

import enums.ItemType;
import visitor.IVisitor;
import java.time.LocalDateTime;

public class NormalItem extends Item {
    public NormalItem(String name, String barcode, float price, float weight, float discount, int ageRestriction, LocalDateTime discountEnd, int quantity) {
        super(name, barcode, price, weight, discount, ageRestriction, discountEnd, quantity);
        this.type = ItemType.NORMAL;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

