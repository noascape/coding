package main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import config.PAModule;
import customer.*;
import database.DatabaseService;
import enums.PaymentType;
import facade.ISBFacade;
import lombok.extern.slf4j.Slf4j;
import visitor.PricingVisitor;

@Slf4j
public class Application {
    public static void main(String... args) {
        DatabaseService dbService = new DatabaseService();
        ShoppingCart cart = new ShoppingCart();

        Item milk = dbService.getItemByBarcode("1001");
        Item apple = dbService.getItemByBarcode("1002");
        Item pen  = dbService.getItemByBarcode("1020");

        cart.addItem(milk);
        cart.addItem(apple);
        cart.addItem(pen);

        PricingVisitor visitor = new PricingVisitor();
        float totalPrice = cart.calculateTotalPrice(visitor);

        log.info("Total price of cart: {} €", totalPrice);



        PAModule module = new PAModule();
        Injector injector = Guice.createInjector(module);
        ISBFacade facade = injector.getInstance(ISBFacade.class);

        //simulate the system operations
        facade.scanItem("1001", 2);
        facade.processPayment(PaymentType.CARD_PAYMENT);


        module.shutdown();
    }
}
