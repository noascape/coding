package human;

import java.util.Map;
import java.util.TreeMap;

public class Passenger {
    private String key;
    private String name;
    private String passportID;
    private int countCheckedBaggage;
    private String checkedBaggage;
    private boolean hasWarrant;
    private Map<String, String> tickets;
    private String bookingClass;
    private String seat;

    public Passenger(String key, String name, String passportID, int countCheckedBaggage, String checkedBaggage, boolean hasWarrant) {
        this.key = key;
        this.name = name;
        this.passportID = passportID;
        this.countCheckedBaggage = countCheckedBaggage;
        this.checkedBaggage = checkedBaggage;
        this.hasWarrant = hasWarrant;
        this.tickets = new TreeMap<>();
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getPassportID() {
        return passportID;
    }

    public int getCountCheckedBaggage() {
        return countCheckedBaggage;
    }

    public String getCheckedBaggage() {
        return checkedBaggage;
    }

    public boolean hasWarrant() {
        return hasWarrant;
    }

    public Map<String, String> getTickets() {
        return tickets;
    }

    public void addTicket(String key, String value) {
        this.tickets.put(key, value);
        if (key.equals("bookingClass")) {
            this.bookingClass = value;
        } else if (key.equals("seat")) {
            this.seat = value;
        }
    }

    public String getBookingClass() {
        return bookingClass;
    }

    public String getSeat() {
        return seat;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "Key='" + key + '\'' +
                ", Name='" + name + '\'' +
                ", PassportID='" + passportID + '\'' +
                ", CountCheckedBaggage=" + countCheckedBaggage +
                ", CheckedBaggage='" + checkedBaggage + '\'' +
                ", hasWarrant=" + hasWarrant +
                ", tickets=" + tickets +
                ", bookingClass='" + bookingClass + '\'' +
                ", seat='" + seat + '\'' +
                '}';
    }
}
