package main;

import SB.SB;
import com.google.inject.Guice;
import com.google.inject.Injector;
import config.PAModule;
import customer.*;
import enums.PaymentType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
    public static void main(String[] args) {
        PAModule module = new PAModule();
        Injector injector = Guice.createInjector(module);

        SB sb = injector.getInstance(SB.class);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new ShoppingCartItem("Milk", "1001", 2, 2f));    //normal 2€
        cart.addItem(new ShoppingCartItem("Apple", "1002", 4, 0.6f));   //weight_based 3€
        cart.addItem(new ShoppingCartItem("Chips", "1004", 1, 0.3f));   //discounted 1,08€
        cart.addItem(new ShoppingCartItem("Vodka", "1006", 1, 0.7f));   //age restricted --> 0€  insgesamt: 6,08€

        IDCard id = new IDCard(16);
        Customer john = new Customer(id, cart, sb);

        //john.touch(sb);
        john.scanItems(sb);
        john.pay(PaymentType.CASH_PAYMENT);

        module.shutdown();
    }
}
