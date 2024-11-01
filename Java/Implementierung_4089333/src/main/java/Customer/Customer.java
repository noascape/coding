package Customer;

import java.util.List;
import java.util.Random;
import Database.Item;
import PA.ButtonType;
import PA.DepositMachine;
import PA.InsertionSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@AllArgsConstructor
@Slf4j
public class Customer {
    private final String name;
    private final Smartphone smartphone;
    private final TrashCan trashCan;
    private final List<Item> items;

    public void insertAllItems(InsertionSlot insertionSlot, DepositMachine depositMachine) {
        for (Item item : items) {
            log.info("Inserting item with barcode {}", item.getBarcode());
            insertionSlot.receiveItem(item);
        }
        log.info("{} presses the Finish button", name);
        depositMachine.getDisplay().pressButton(ButtonType.FINISH);

        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < 80) {
            log.info("{} presses the Donation button", name);
            depositMachine.getDisplay().pressButton(ButtonType.DONATION);
        } else {
            log.info("{} presses the Deposit Receipt button", name);
            depositMachine.getDisplay().pressButton(ButtonType.DEPOSIT_RECEIPT);
            log.info("{} holds his card up to the reader", name);
            depositMachine.getReader().scanSmartphone(smartphone);
            depositMachine.getCentralUnit().generateReceipt();
        }
        depositMachine.getCentralUnit().clearVariables();
        depositMachine.getCentralUnit().incrementSequenceNumber();
    }

    public void removeItem(Item item) {
        log.info("{} removes item with barcode {} from the slot and throws it into the trashcan.", name, item.getBarcode());
        trashCan.getTrashContainer().push(item);
    }
}

