package PA;

import Customer.Smartphone;
import Database.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Slf4j
public class CentralUnit {
    private final DepositMachine depositMachine;
    private final Set<Double> registeredCards = new HashSet<>();
    private int insertedItems = 0;
    private int acceptedItems = 0;
    private int nonAcceptedItems = 0;
    private int disposableCount = 0;
    private int reusableCount = 0;
    private double disposableTotal = 0.0;
    private double reusableTotal = 0.0;
    private double totalForAll = 0.0;
    private int sequenceNumber = 1;
    private final List<String> donationReceipts = new ArrayList<>();


    public CentralUnit(DepositMachine depositMachine) {
        this.depositMachine = depositMachine;  // Store reference to DepositMachine
    }

    public void registerCard(double cardID) {
        log.info("Registered Card: {}", cardID);
        registeredCards.add(cardID);
    }

    public void authenticateCard(double cardID) {
        log.info("Card with ID {} is being checked", cardID);
        if (registeredCards.contains(cardID)) {
            log.info("Card approved");
            if (depositMachine.getLed().getStatus() == LEDStatus.RED) {
                log.info("Machine unlocked by card ID: {}", cardID);
                depositMachine.setReadyStatus();
            } else {
                log.info("Machine locked by card ID: {}", cardID);
                depositMachine.getLed().updateStatus(LEDStatus.RED);
                depositMachine.setStatus(DepositMachine.STATUS_LOCKED);
            }
        } else {
            log.warn("Unauthorized card ID: {}", cardID);
            depositMachine.getDisplay().showMessage("Access denied.");
        }
    }

    public void sortItem(Item item) {
        this.totalForAll = totalForAll + item.getDepositAmount();
        if (item.getRecyclingType() == RecyclingType.SINGLE_USE) {
            depositMachine.getWasteContainer().compress(item);
            depositMachine.getWasteContainer().addCan(item);
            incrementDisposableCount();
            this.disposableTotal = disposableTotal + item.getDepositAmount();
        } else if (item.getRecyclingType() == RecyclingType.MULTI_USE) {
            incrementReusableCount();
            this.reusableTotal = reusableTotal + item.getDepositAmount();
            switch (item.getMaterialType()) {
                case PLASTIC:
                    depositMachine.getWasteContainer().addPlasticBottle(item);
                    break;
                case GLASS:
                    depositMachine.getWasteContainer().addGlassBottle(item);
                    break;
                default:
                    log.warn("Unknown item type");
                    depositMachine.getInsertionSlot().returnItem(item);
            }
        }
        depositMachine.setReadyStatus();
        showList();

    }
    public void showList(){
        depositMachine.getDisplay().showMessage("#inserted items: " + insertedItems);
        depositMachine.getDisplay().showMessage("#accepted items: " + acceptedItems);
        depositMachine.getDisplay().showMessage("#disposable: " + disposableCount + " (" + String.format("%.2f", disposableTotal) + " €)");
        depositMachine.getDisplay().showMessage("#reusable: " + reusableCount + " (" + String.format("%.2f", reusableTotal) + " €)");
        depositMachine.getDisplay().showMessage("#non-accepted items: " + nonAcceptedItems);
        depositMachine.getDisplay().showMessage("> total: " + String.format("%.2f", totalForAll) + " €");
        depositMachine.getDisplay().showButton(depositMachine.getFinishButton());

    }
    public void finishTransaction() {
        depositMachine.getDisplay().showButton(depositMachine.getDonationButton());
        depositMachine.getDisplay().showButton(depositMachine.getDepositReceiptButton());
    }

    public String generateReceipt() {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        int serialNumber = depositMachine.hashCode();

        StringBuilder receipt = new StringBuilder();
        receipt.append("\nSeq: ").append(sequenceNumber).append("\n");
        receipt.append(dateTime).append("\n");
        receipt.append("Serialnumber: ").append(serialNumber).append("\n");
        receipt.append("#inserted items: ").append(insertedItems).append("\n");
        receipt.append("#accepted items: ").append(acceptedItems).append("\n");
        receipt.append("#disposable: ").append(disposableCount).append(" (")
                .append(String.format("%.2f", disposableTotal)).append(" €)\n");
        receipt.append("#reusable: ").append(reusableCount).append(" (")
                .append(String.format("%.2f", reusableTotal)).append(" €)\n");
        receipt.append("#non-accepted items: ").append(nonAcceptedItems).append("\n");
        receipt.append("> total: ").append(String.format("%.2f", totalForAll)).append(" €\n");

        depositMachine.getDisplay().showMessage(receipt.toString());
        return receipt.toString();
    }

    public void donation() {
        String receipt = generateReceipt();
        donationReceipts.add(receipt);
        depositMachine.setReadyStatus();
    }

    public void depositReceipt() {
        depositMachine.setStatus(DepositMachine.STATUS_ACTION);
        depositMachine.getLed().updateStatus(LEDStatus.YELLOW);
        depositMachine.getDisplay().showMessage("place your smartphone on reader");
    }

    public void transferToWallet(Smartphone smartphone) {
        smartphone.addToWallet(totalForAll);
        depositMachine.setReadyStatus();

    }

    public void clearVariables() {
        this.insertedItems = 0;
        this.acceptedItems = 0;
        this.disposableCount = 0;
        this.reusableCount = 0;
        this.nonAcceptedItems = 0;
        this.disposableTotal = 0.0;
        this.reusableTotal = 0.0;
        this.totalForAll = 0.0;
    }

    public void incrementInsertedItems() {
        insertedItems++;
    }

    public void incrementAcceptedItems() {
        acceptedItems++;
    }

    public void incrementNonAcceptedItems() {
        nonAcceptedItems++;
    }

    public void incrementDisposableCount() {
        disposableCount++;
    }

    public void incrementReusableCount() {
        reusableCount++;
    }

    public void incrementSequenceNumber() {
        sequenceNumber++;
    }
}

