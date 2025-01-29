package visitor;

import customer.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class PricingVisitor implements IVisitor {
    private float totalPrice = 0f;

    @Override
    public void visit(NormalItem item) {
        log.info("Calculating price for normal item: {}", item.getName());
        totalPrice += item.getPrice() * item.getQuantity();
    }

    @Override
    public void visit(WeightBasedItem item) {
        log.info("Calculating price for weight-based item: {}", item.getName());
        float weightDiff = item.getWeight() / 0.1f;
        totalPrice += (item.getPrice() * weightDiff);
    }

    @Override
    public void visit(DiscountedItem item) {
        if (item.isDiscountActive()) {
            float discountedPrice = item.getPrice() -  (item.getPrice() * item.getDiscount());
            log.info("Calculating discounted price for item: {}", item.getName());
            totalPrice += discountedPrice * item.getQuantity();
        } else {
            log.info("Discount expired, normal price for item: {}", item.getName());
            totalPrice += item.getPrice() * item.getQuantity();
        }
    }

    @Override
    public void visit(AgeRestrictedItem item) {
        log.info("Calculating price for age-restricted item: {}", item.getName());
        totalPrice += item.getPrice() * item.getQuantity();
    }
}

