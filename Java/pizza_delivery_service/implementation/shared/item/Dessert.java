package shared.item;

import dp.visitor.CartVisitor;
import dp.visitor.ICartItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import shared.enums.dessert.DessertType;

@RequiredArgsConstructor
@Getter
public class Dessert implements ICartItem {
    private final String name;
    private final double price;
    private final DessertType type;

    public double accept(CartVisitor visitor) {
        return visitor.visit(this);
    }
}