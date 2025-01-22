package main;

import dp.builder.Pizza;
import dp.command.Command;
import dp.command.PreparePizzaCommand;
import dp.decorator.DangerousAreaDelivery;
import dp.decorator.IDelivery;
import dp.decorator.NightDelivery;
import dp.decorator.NormalDelivery;
import dp.memento.PizzaOrderCaretaker;
import dp.observer.Pager;
import dp.visitor.CartCalculationVisitor;
import dp.visitor.CartVisitor;
import lombok.extern.slf4j.Slf4j;
import shared.PizzaMaker;
import shared.PizzaStatus;
import shared.cart.Cart;
import shared.enums.dessert.DessertType;
import shared.enums.drink.DrinkType;
import shared.enums.pizza.DoughType;
import shared.enums.pizza.SauceType;
import shared.enums.pizza.Topping;
import shared.item.Dessert;
import shared.item.Drink;
import shared.order.Order;
import shared.order.PizzaOrderSystem;

import java.util.List;

@Slf4j
public class Application {
    public static void main(String... args) {
        // builder and memento
        PizzaOrderCaretaker caretaker = new PizzaOrderCaretaker();

        Pizza pizza01 = new Pizza.PizzaBuilder()
                .setDough(DoughType.THIN_CRUST)
                .setSauce(SauceType.TOMATO)
                .addToppings(List.of(Topping.CHEESE, Topping.PEPPERONI))
                .build();
        caretaker.save(pizza01);
        log.info("initial pizza: {}", pizza01);

        Pizza pizza02 = new Pizza.PizzaBuilder()
                .setDough(pizza01.getDough())
                .setSauce(pizza01.getSauce())
                .addToppings(pizza01.getToppings())
                .addTopping(Topping.MUSHROOMS)
                .addTopping(Topping.JALAPENOS)
                .build();
        caretaker.save(pizza02);
        log.info("modified pizza: {}", pizza02);

        Pizza pizza03 = new Pizza.PizzaBuilder()
                .setDough(pizza02.getDough())
                .setSauce(SauceType.BBQ)
                .addToppings(pizza02.getToppings())
                .build();
        log.info("modified pizza: {}", pizza03);

        Pizza pizza = caretaker.undo();
        log.info("final pizza: {}", pizza);

        // visitor
        Cart cart = new Cart();
        cart.addItem(new Drink("Cola", 2.50, DrinkType.NON_ALCOHOLIC));
        cart.addItem(new Drink("Beer", 4.00, DrinkType.ALCOHOLIC));
        cart.addItem(new Drink("Water", 1.00, DrinkType.NON_ALCOHOLIC));
        cart.addItem(pizza);
        cart.addItem(new Dessert("Fruit Salad", 5.00, DessertType.LOW_SUGAR));
        cart.addItem(new Dessert("Chocolate Cake", 7.00, DessertType.HIGH_SUGAR));
        CartVisitor visitor = new CartCalculationVisitor();
        double total = cart.calculateTotal(visitor);
        log.info("total: {}", total);

        // order
        Order order = cart.toOrder(visitor);
        log.info("order: {}", order);

        // command
        PizzaMaker pizzaMaker = new PizzaMaker("Antonio");
        PizzaOrderSystem orderSystem = new PizzaOrderSystem();
        Command preparePizza = new PreparePizzaCommand(pizzaMaker, pizza);
        orderSystem.placeOrder(preparePizza);
        orderSystem.showOrderHistory();

        // decorator
        IDelivery delivery = new DangerousAreaDelivery(
                new NightDelivery(
                        new NormalDelivery()));

        double totalDeliveryCost = delivery.getTotalIncludingDelivery(order);
        String deliveryDescription = delivery.getDescription();
        log.info("totalDeliveryCost: {}", totalDeliveryCost);
        log.info("deliveryDescription: {}", deliveryDescription);

        // observer
        Pager pager = new Pager("John");
        pizza.addObserver(pager);
        pizza.setStatus(PizzaStatus.READY_FOR_DELIVERY);
    }
}