package PA;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class Display {
    private final DepositMachine depositMachine;

    public void showMessage(String message) {
        log.info("Display: {}", message);
    }

    public void showButton(ButtonType buttonType) {
        log.info("Button displayed: {}", buttonType);
    }

    public void pressButton(ButtonType buttonType) {
        switch (buttonType) {
            case FINISH -> depositMachine.getCentralUnit().finishTransaction();
            case DONATION -> depositMachine.getCentralUnit().donation();
            case DEPOSIT_RECEIPT -> depositMachine.getCentralUnit().depositReceipt();
        }
    }
}


