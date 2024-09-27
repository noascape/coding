package Customer;
import Bank.*;
import Automat.*;

public class Smartphone {
    private Customer owner;
    private String pin;
    private Bankaccount bankaccount;
    private Ticket ticket;


    public Smartphone(Customer owner, String pin, Bankaccount bankaccount){
        this.owner = owner;
        this.pin = pin;
        this.bankaccount = bankaccount;
    }

    public boolean receiveMessage(String type, double price) {

        System.out.println("Neue Transaktion vom Typ: " + type + " ist eingegangen. Die Kosten betragen " + price + "€");

        //Weiterleiten an Kunde zur Bestätigung
        return owner.approveTransaction(pin);

    }

    public Bankaccount getBankaccount() {
        return bankaccount;
    }

    public void receiveTicket (Ticket ticket, String type) {
        this.ticket = ticket;
        System.out.println("Hier das " + type + ": " + ticket);
    }
}
