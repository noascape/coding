package junit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import config.PAModule;
import database.DatabaseService;
import enums.PaymentType;
import facade.ISBFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import visitor.PricingVisitor;
import customer.Item;
import customer.ShoppingCart;

public class JunitTest {

    @Test
    void testScanAndPayFlow() {
        // 1) Wir bauen das System auf:
        PAModule module = new PAModule();
        Injector injector = Guice.createInjector(module);

        // 2) DatabaseService anlegen, um Artikel zu holen
        DatabaseService dbService = new DatabaseService();

        // 3) Einen kleinen Warenkorb anlegen
        ShoppingCart cart = new ShoppingCart();
        Item milk = dbService.getItemByBarcode("1001"); // Normal
        Item apple = dbService.getItemByBarcode("1002"); // Weight-based
        Assertions.assertNotNull(milk);
        Assertions.assertNotNull(apple);

        cart.addItem(milk);
        cart.addItem(apple);

        PricingVisitor visitor = new PricingVisitor();
        float totalPrice = cart.calculateTotalPrice(visitor);

        // Sanity-Check: Haben wir wirklich >0 berechnet?
        Assertions.assertTrue(totalPrice > 0, "Total price should be > 0");

        // 4) ISBFacade holen und Operationen anstoßen
        ISBFacade facade = injector.getInstance(ISBFacade.class);

        // Simuliere: Der Kunde scannt "1001" * 2
        facade.scanItem("1001", 2);

        // Jetzt zahlt der Kunde per Karte
        facade.processPayment(PaymentType.CARD_PAYMENT);

        // 5) Zum Schluss "Shut Down"
        module.shutdown();
    }
}
