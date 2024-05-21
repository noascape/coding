package bottle;

public class BackLabel extends Label{
    String advice = "Drink responsibly";
    long timestamp;
    int seq;

    public BackLabel(long timestamp, int serialNumber) {
        this.timestamp = timestamp;
        this.seq = serialNumber;
    }

}
