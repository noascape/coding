package customer;

import visitor.IVisitor;

public class NormalItem extends Item {

    public NormalItem(String itemName, String barcode, float price, float weight, int ageRestriction) {
        super(itemName, barcode, price, weight, ageRestriction);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

