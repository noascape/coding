package dp.visitor;

import dp.builder.Pizza;
import shared.enums.dessert.DessertType;
import shared.enums.drink.DrinkType;
import shared.item.Dessert;
import shared.item.Drink;

public class CartCalculationVisitor extends CartVisitor {
    private static final double NON_ALCOHOLIC_TAX = 0.10;
    private static final double ALCOHOLIC_TAX = 0.30;
    private static final double PIZZA_TAX = 0.15;
    private static final double LOW_SUGAR_TAX = 0.10;
    private static final double HIGH_SUGAR_TAX = 0.50;

    private int drinkCount = 0;
    private int pizzaCount = 0;

    public double visit(Drink drink) {
        double taxRate = drink.getType() == DrinkType.NON_ALCOHOLIC ? NON_ALCOHOLIC_TAX : ALCOHOLIC_TAX;
        double priceWithTax = drink.getPrice() * (1 + taxRate);

        if (++drinkCount == 3) {
            priceWithTax *= 0.5;
        }

        return priceWithTax;
    }

    public double visit(Pizza pizza) {
        double priceWithTax = pizza.calculateCost() * (1 + PIZZA_TAX);

        if (++pizzaCount == 2) {
            pizzaCount = 0;
            return priceWithTax;
        }

        return priceWithTax;
    }

    public double visit(Dessert dessert) {
        double taxRate = dessert.getType() == DessertType.LOW_SUGAR ? LOW_SUGAR_TAX : HIGH_SUGAR_TAX;
        return dessert.getPrice() * (1 + taxRate);
    }
}