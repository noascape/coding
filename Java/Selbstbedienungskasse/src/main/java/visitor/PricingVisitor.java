package visitor;

import customer.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PricingVisitor implements IVisitor {
    private float totalPrice = 0f;

    @Override
    public void visit(NormalItem item) {
        log.info("Calculating price for normal item: {}", item.getItemName());
        totalPrice += item.getPrice();
    }

    @Override
    public void visit(WeightBasedItem item) {
        log.info("Calculating price for weight-based item: {}", item.getItemName());
        totalPrice += item.getPrice() * item.getWeight();
    }

    @Override
    public void visit(DiscountedItem item) {
        float discountedPrice = item.getPrice() - (item.getPrice() * item.getDiscount());
        log.info("Calculating discounted price for item: {}", item.getItemName());
        totalPrice += discountedPrice;
    }

    @Override
    public void visit(AgeRestrictedItem item) {
        log.info("Calculating price for age-restricted item: {}", item.getItemName());
        totalPrice += item.getPrice();
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}

