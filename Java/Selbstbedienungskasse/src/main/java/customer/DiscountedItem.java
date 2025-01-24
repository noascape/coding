package customer;

import visitor.IVisitor;

public class DiscountedItem extends Item {
    private final float discount;

    public DiscountedItem(String itemName, String barcode, float price, float weight, float discount) {
        super(itemName, barcode, price, weight, 0);
        this.discount = discount;
    }

    public float getDiscount() {
        return discount;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

