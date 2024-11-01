package PA;

import Database.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ConveyingSystem {
    private final DepositMachine depositMachine;

    public void transferItem(Item item) {
        String itemBarcode = depositMachine.getRotationUnit().scanItem(item);
        if (itemBarcode == null){
            log.info("Barcode unknown");
            depositMachine.setStatus(DepositMachine.STATUS_ACTION);
            depositMachine.getLed().updateStatus(LEDStatus.YELLOW);
            depositMachine.getInsertionSlot().returnItem(item);
            depositMachine.getCentralUnit().incrementNonAcceptedItems();
        } else {
            Item foundItem = depositMachine.getDatabase().getItem(itemBarcode);
            if (foundItem != null){
                depositMachine.getCentralUnit().incrementAcceptedItems();
                depositMachine.getCentralUnit().sortItem(foundItem);
            } else {
                depositMachine.setStatus(DepositMachine.STATUS_ACTION);
                depositMachine.getLed().updateStatus(LEDStatus.YELLOW);
                depositMachine.getInsertionSlot().returnItem(item);
                depositMachine.getCentralUnit().incrementNonAcceptedItems();
                if (depositMachine.getCentralUnit().getAcceptedItems() > 0) {
                    depositMachine.getCentralUnit().showList();
                }
            }
        }
    }
}

