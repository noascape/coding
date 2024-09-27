public class Automat {
    private Bank bank;
    private Ticket ticket;

    public Automat(Bank bank, Ticket ticket) {
        this.bank = bank;
        this.ticket = ticket;
    }

    public void message(Smartphone smartphone) {
        //Smartphone gets all the Information and the Customer can then approve with his pin
        if (smartphone.receiveMessage(ticket.getType(), ticket.getPrice())) {
            if (bank.check()) {
                System.out.println("Ausreichende Deckung und gültige Pin  --> Transaktion genehmigt");
                //sende Ticket an Smartphone des Cunden
            } else {
                System.out.println("Nachricht abgelehnt (kommt aber von Bank");
            }


    }
}
