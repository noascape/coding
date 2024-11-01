package Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Smartphone {
    private double wallet;

    public void addToWallet(double amount) {
        wallet += amount;
        log.info("Wallet balance: {} €", wallet);
    }
}

