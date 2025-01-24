package customer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Customer {
    private final String name;
    private final int age;
    private ShoppingCart cart;         //wäre eine Idee
}
