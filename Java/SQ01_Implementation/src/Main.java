import Automat.*;
import Bank.*;
import Customer.*;

public class Main {
    public static void main(String[] args) {

        Customer John = new Customer();
        Bankaccount bankaccount = new Bankaccount(1000);
        Smartphone Iphone = new Smartphone(John, "1234", bankaccount);

        Automat automat = new Automat(new Bank(), new Ticket("Zugticket", 10));
        automat.message(Iphone);
    }
}