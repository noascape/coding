package customer;

import visitor.IVisitor;

public class WeightBasedItem extends Item {

    public WeightBasedItem(String itemName, String barcode, float price, float weight) {
        super(itemName, barcode, price, weight, 0);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

