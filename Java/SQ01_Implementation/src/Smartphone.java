public class Smartphone {
    Customer owner;
    String pin;


    Smartphone(Customer owner, String pin){
        this.owner = owner;
        this.pin = pin;
    }



    public boolean receiveMessage(String type, double price) {

        System.out.println("Neue Transaktion vom Typ: " + type + " ist eingegangen. Die Kosten betragen " + price + "€");

        //Weiterleiten an Kunde zur Bestätigung
        return owner.approveTransaction(pin);

    }

    public void receiveTicket (Ticket ticket) {
        System.out.println("Hier das Ticket" + ticket);
    }
}
