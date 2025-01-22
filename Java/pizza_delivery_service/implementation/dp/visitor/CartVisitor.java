package dp.visitor;

import dp.builder.Pizza;
import shared.item.Dessert;
import shared.item.Drink;

public abstract class CartVisitor {
    public abstract double visit(Drink drink);

    public abstract double visit(Pizza pizza);

    public abstract double visit(Dessert dessert);
}