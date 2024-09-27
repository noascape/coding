package Bank;

public class Bankaccount {
    private double balance;


    public Bankaccount (double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deductAmount(double amount) {
            balance -= amount;
            System.out.println("Es wurden  " + amount + "€ vom Konto abgezogen. Neuer Kontostand: " + balance + "€");

    }
}
