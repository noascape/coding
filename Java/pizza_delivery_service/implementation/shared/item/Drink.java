package shared.item;

import dp.visitor.CartVisitor;
import dp.visitor.ICartItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import shared.enums.drink.DrinkType;

@RequiredArgsConstructor
@Getter
@ToString
public class Drink implements ICartItem {
    private final String name;
    private final double price;
    private final DrinkType type;

    public double accept(CartVisitor visitor) {
        return visitor.visit(this);
    }
}