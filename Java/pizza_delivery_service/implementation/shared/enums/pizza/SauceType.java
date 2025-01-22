package shared.enums.pizza;

import lombok.Getter;

@Getter
public enum SauceType {
    TOMATO(1.5), BBQ(2.0), PESTO(2.5), NONE(0.0);

    private final double cost;

    SauceType(double cost) {
        this.cost = cost;
    }
}