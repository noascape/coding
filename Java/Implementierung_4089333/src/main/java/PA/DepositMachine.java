package PA;

import RotationUnit.RotationUnit;
import Database.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class DepositMachine {
    public static final String STATUS_LOCKED = "gesperrt";
    public static final String STATUS_READY = "Bereit zur Annahme";
    public static final String STATUS_ACTION = "Aktion erforderlich";

    private final Display display;
    private final Database database;
    private final InsertionSlot insertionSlot;
    private final LED led;
    private final ConveyingSystem conveyingSystem;
    private final RotationUnit rotationUnit;
    private final WasteContainer wasteContainer;  //noch keine Logik
    private final CentralUnit centralUnit;
    private final Reader reader;

    private final ButtonType finishButton;
    private final ButtonType donationButton;
    private final ButtonType depositReceiptButton;
    @Setter
    private String status;

    public DepositMachine() {
        this.display = new Display(this);
        this.database = new Database();
        this.insertionSlot = new InsertionSlot(this, null);
        this.led = new LED();
        this.conveyingSystem = new ConveyingSystem(this);
        this.rotationUnit = new RotationUnit();
        this.wasteContainer = new WasteContainer();
        this.centralUnit = new CentralUnit(this);
        this.reader = new Reader(this.centralUnit);
        this.status = STATUS_LOCKED;
        this.finishButton = ButtonType.FINISH;
        this.donationButton = ButtonType.DONATION;
        this.depositReceiptButton = ButtonType.DEPOSIT_RECEIPT;
    }

    public void setReadyStatus() {
        this.status = STATUS_READY;
        this.led.updateStatus(LEDStatus.GREEN);
        this.display.showMessage("Ready.");
    }

    public void setLockedStatus() {
        this.status = STATUS_LOCKED;
        this.led.updateStatus(LEDStatus.RED);
        this.display.showMessage("Processing item | please wait.");
    }
}


