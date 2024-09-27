public class Ticket {
    private String type;
    private double price;

    Ticket(String type, double price) {
        this.type = type;
        this.price = price;
    }


    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
