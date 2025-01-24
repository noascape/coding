package SB;

import SB.barcodeScanner.BarcodeScanner;
import SB.weightPlatform.WeightPlatform;
import enums.State;

public class SB {
    private final BarcodeScanner barcodeScanner;
    private final WeightPlatform weightPlatform;
    private final Touchscreen touchscreen;
    private final Display display;
    private State state;

    public SB(BarcodeScanner barcodeScanner, WeightPlatform weightPlatform, Touchscreen touchscreen, Display display) {
        this.barcodeScanner = barcodeScanner;
        this.weightPlatform = weightPlatform;
        this.touchscreen = touchscreen;
        this.display = display;
        this.state = State.INACTIVE;
    }

    public void start() {
        this.state = State.ACTIVE;
        barcodeScanner.activate();
        weightPlatform.calibrate();
        display.show("Kasse bereit");
    }

}
