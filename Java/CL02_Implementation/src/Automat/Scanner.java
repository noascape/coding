package Automat;
import ProcessingUnit.ProcessingUnit;
import ProcessingUnit.Processor;

public class Scanner {
    private final Cooler cooler;
    private final Processor processor;

    public Scanner(Processor processor, Cooler cooler) {
        this.cooler = cooler;
        this.processor = processor;
    }

    void receiveString(String str) {
        System.out.println("Received: " + str);
    }

    void sendString(String str) {
        System.out.println("Sending: " + str);
    }

    void release(Beverage beverage) {
        System.out.println("Releasing: " + beverage);
    }
}
