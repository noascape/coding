package shared.enums.pizza;

import lombok.Getter;

@Getter
public enum Topping {
    CHEESE(1.0), PEPPERONI(1.5), MUSHROOMS(1.0), ONIONS(0.5),
    OLIVES(0.5), JALAPENOS(0.5), CHICKEN(2.0), VEGGIES(1.0);

    private final double cost;

    Topping(double cost) {
        this.cost = cost;
    }
}