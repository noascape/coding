package shared.enums.pizza;

import lombok.Getter;

@Getter
public enum DoughType {
    THIN_CRUST(5.0), THICK_CRUST(6.0), WHOLE_WHEAT(7.0), GLUTEN_FREE(8.0);

    private final double cost;

    DoughType(double cost) {
        this.cost = cost;
    }
}