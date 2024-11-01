package Customer;

import java.util.List;
import java.util.Arrays;

public class CustomerHelper {
    public static List<Customer> createCustomers() {
        TrashCan trashCan = TrashCan.getInstance();
        return Arrays.asList(
                new Customer("Customer1", new Smartphone(), trashCan, TestData.getTestData1()),
                new Customer("Customer2", new Smartphone(), trashCan, TestData.getTestData2()),
                new Customer("Customer3", new Smartphone(), trashCan, TestData.getTestData3()),
                new Customer("Customer4", new Smartphone(), trashCan, TestData.getTestData4()),
                new Customer("Customer5", new Smartphone(), trashCan, TestData.getTestData5()),
                new Customer("Customer6", new Smartphone(), trashCan, TestData.getTestData6()),
                new Customer("Customer7", new Smartphone(), trashCan, TestData.getTestData7()),
                new Customer("Customer8", new Smartphone(), trashCan, TestData.getTestData8())
        );
    }
}



