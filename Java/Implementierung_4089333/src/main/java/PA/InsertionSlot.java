package PA;

import Database.Item;
import Customer.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class InsertionSlot {
    private final DepositMachine depositMachine;
    private Customer currentCustomer;

    public void setCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    public void receiveItem(Item item) {
        depositMachine.getCentralUnit().incrementInsertedItems();
        if (depositMachine.getLed().getStatus() == LEDStatus.GREEN &&
            depositMachine.getStatus().equals(DepositMachine.STATUS_READY)) {
            depositMachine.setLockedStatus();
            depositMachine.getConveyingSystem().transferItem(item);
        } else {
            log.info("Insertion is not possible");
        }
    }

    public void returnItem(Item item) {
        depositMachine.getDisplay().showMessage("Please remove item from insertion slot.");
        currentCustomer.removeItem(item);
        depositMachine.setReadyStatus();
    }
}

