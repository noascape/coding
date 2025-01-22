package dp.decorator;

import lombok.Getter;

@Getter
public enum DeliveryType {
    NORMAL(3),
    EXPRESS(5),
    CASH_PAYMENT(2),
    ALUMINUM_FOIL(5),
    HEATING_BOX(2),
    NIGHT_DELIVERY(3),
    DANGEROUS_AREA(5);

    private final double cost;

    DeliveryType(double cost) {
        this.cost = cost;
    }
}