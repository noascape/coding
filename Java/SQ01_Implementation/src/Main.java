public class Main {
    public static void main(String[] args) {

        Customer John = new Customer();
        Smartphone Iphone = new Smartphone(John, "1234");

        Automat automat = new Automat(new Bank(), new Ticket("Zugticket", 10));
        automat.message(Iphone);
    }
}