package Automat;

import Bank.Bank;
import Customer.Smartphone;

public class Automat {
    private final Bank bank;
    private final Ticket ticket;

    public Automat(Bank bank, Ticket ticket) {
        this.bank = bank;
        this.ticket = ticket;
    }

    public void message(Smartphone smartphone) {
        //Customer.Smartphone gets all the Information and the Customer.Customer can then approve with his pin
        if (smartphone.receiveMessage(ticket.getType(), ticket.getPrice())) {

            if (bank.checkBalance(smartphone.getBankaccount(), ticket.getPrice())) {
                smartphone.receiveTicket(ticket, ticket.getType());

            } else {
                System.out.println("Der Vorgang wurde abgebrochen!");
            }
        } else {
            System.out.println("Der Vorgang wurde abgebrochen!");
        }
    }
}