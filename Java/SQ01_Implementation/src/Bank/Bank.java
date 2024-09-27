package Bank;

import Customer.Customer;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Customer, Bankaccount> accounts = new HashMap<>();

    public void addAccount(Customer customer, Bankaccount bankaccount) {
        accounts.put(customer, bankaccount);
    }

    public boolean checkBalance(Bankaccount bankaccount, double price) {
        if (bankaccount.getBalance() - price > 0) {
            bankaccount.deductAmount(price);
            System.out.println("Ausreichende Deckung und gültige Pin  --> Transaktion genehmigt");
            return true;
        }
        System.out.println("Nicht genug Guthaben auf dem Konto. Die Transaktion wurde abgelehnt");
        return false;
    }
}
