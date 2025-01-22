package dp.builder;

import dp.observer.IObserver;
import dp.observer.ISubject;
import dp.visitor.CartVisitor;
import dp.visitor.ICartItem;
import lombok.Getter;
import lombok.ToString;
import shared.PizzaStatus;
import shared.enums.pizza.DoughType;
import shared.enums.pizza.SauceType;
import shared.enums.pizza.Topping;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Pizza implements ICartItem, ISubject {
    private final DoughType dough;
    private final SauceType sauce;
    private final List<Topping> toppings;
    private final List<IObserver> observers;
    private PizzaStatus status;

    private Pizza(PizzaBuilder builder) {
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
        status = PizzaStatus.ORDERED;
        observers = new ArrayList<>();
    }

    public double accept(CartVisitor visitor) {
        return visitor.visit(this);
    }

    public double calculateCost() {
        double doughCost = dough.getCost();
        double sauceCost = sauce.getCost();
        double toppingsCost = toppings.stream().mapToDouble(Topping::getCost).sum();
        return doughCost + sauceCost + toppingsCost;
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
        notifyObservers();
    }

    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(status);
        }
    }

    public static class PizzaBuilder {
        private final List<Topping> toppings = new ArrayList<>();
        private DoughType dough;
        private SauceType sauce;

        public PizzaBuilder setDough(DoughType dough) {
            this.dough = dough;
            return this;
        }

        public PizzaBuilder setSauce(SauceType sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder addTopping(Topping topping) {
            this.toppings.add(topping);
            return this;
        }

        public PizzaBuilder addToppings(List<Topping> toppings) {
            this.toppings.addAll(toppings);
            return this;
        }

        public Pizza build() {
            if (dough == null || sauce == null) {
                throw new IllegalArgumentException("dough and sauce must be specified.");
            }
            return new Pizza(this);
        }
    }
}