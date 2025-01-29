package junit;

import SB.SB;
import SB.WeightPlatform;
import com.google.inject.Guice;
import com.google.inject.Injector;
import config.PAModule;
import database.DatabaseService;
import enums.PaymentType;
import org.junit.jupiter.api.*;
import services.payment.PaymentService;
import services.scan.IScanService;
import customer.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JunitTest {

    private Injector injector;
    private IScanService scanService;
    private PaymentService paymentService;
    private DatabaseService db;
    private PAModule module;
    private SB sb;

    @BeforeEach
    void setupModule() {
        module = new PAModule();
        injector = Guice.createInjector(module);
        scanService = injector.getInstance(IScanService.class);
        paymentService = injector.getInstance(PaymentService.class);
        db = injector.getInstance(DatabaseService.class);
        sb = injector.getInstance(SB.class);
    }

    @Test
    @Order(1)
    @DisplayName("SB Activation by Scanning")
    void testSBActivationByScanning() throws InterruptedException {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new ShoppingCartItem("Milk", "1001", 2, 2f));
        Customer customer = new Customer(new IDCard(25), cart, sb);
        customer.scanItems(sb);
        assertEquals(enums.State.ACTIVE, sb.getState(), "SB should be active after scanning");
        module.shutdown();
    }

    @Test
    @Order(2)
    @DisplayName("SB Activation by Touchscreen")
    void testSBActivationByTouchscreen() {
        ShoppingCart emptyCart = new ShoppingCart();
        Customer customer = new Customer(new IDCard(30), emptyCart, sb);
        customer.touch(sb);
        assertEquals(enums.State.ACTIVE, sb.getState(), "SB should be active after touching the touchscreen");
        module.shutdown();
    }

    @Test
    @Order(3)
    @DisplayName("Shopping Cart Multiple Items Calculation")
    void testShoppingCartMultipleItemsCalculation() throws InterruptedException {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new ShoppingCartItem("Milk", "1001", 2, 2f));    // NORMAL: 2 * 1€ = 2€
        cart.addItem(new ShoppingCartItem("Apple", "1002", 4, 0.6f)); // WEIGHT_BASED: [0.5€/100g] 6 * 0.5€ = 3€
        cart.addItem(new ShoppingCartItem("Chips", "1004", 1, 0.3f)); // DISCOUNTED: 1.2€ - 10% = 1.08€
        cart.addItem(new ShoppingCartItem("Wisky", "1016", 1, 1f));   // AGE_RESTRICTED: = 20€
        Customer customer = new Customer(new IDCard(20), cart, sb);
        customer.scanItems(sb);
        float expectedTotal = 2f + 3f +1.08f + 20f; //26.08€
        module.shutdown();
        assertEquals(expectedTotal, paymentService.getTotalSum(), "Total sum should be correctly calculated before payment");
    }

    @Test
    @Order(4)
    @DisplayName("Weight Mismatch Handling")
    void testWeightMismatch() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new ShoppingCartItem("Milk", "1001", 2, 1.5f)); // Expected 2kg, provided 1.5kg
        Customer customer = new Customer(new IDCard(25), cart, sb);
        customer.scanItems(sb);
        WeightPlatform weightPlatform = sb.getWeightPlatform();
        assertFalse(weightPlatform.getPlacedItems().contains(cart.getItems().getFirst()), "Item with weight mismatch should not be added");
        module.shutdown();
    }

    @Test
    @Order(5)
    @DisplayName("Discount Functionality")
    void testDiscountFunctionality() throws InterruptedException {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new ShoppingCartItem("Chips", "1004", 1, 0.3f)); // DISCOUNTED: 1.2€ - 10% = 1.08€
        Customer customer = new Customer(new IDCard(22), cart, sb);
        customer.scanItems(sb);
        module.shutdown();
        assertEquals(1.08f, paymentService.getTotalSum(), "10% discount should be correctly calculated");
    }

    @Test
    @Order(6)
    @DisplayName("Expired Discount Handling")
    void testExpiredDiscount() throws InterruptedException {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new ShoppingCartItem("Chocolate", "1011", 1, 0.3f)); // DISCOUNTED but expired: 1.5€ * 1 = 1.5€
        Customer customer = new Customer(new IDCard(28), cart, sb);
        customer.scanItems(sb);
        module.shutdown();
        assertEquals(1.5f, paymentService.getTotalSum(), "Expired discount should not be taken into account");
    }

    @Test
    @Order(7)
    @DisplayName("Age Restricted Item Purchase by Underage Customer")
    void testAgeRestrictedItemPurchase() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new ShoppingCartItem("Vodka", "1006", 1, 0.7f)); // AGE_RESTRICTED: 18+
        Customer customer = new Customer(new IDCard(16), cart, sb); // Underage customer
        customer.scanItems(sb);
        customer.pay(PaymentType.CASH_PAYMENT);
        WeightPlatform weightPlatform = sb.getWeightPlatform();
        assertFalse(weightPlatform.getPlacedItems().contains(cart.getItems().getFirst()), "Age-restricted item should not be added for underage customer");
        module.shutdown();
    }
}
