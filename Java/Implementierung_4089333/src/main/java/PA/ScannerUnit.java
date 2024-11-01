package PA;

import lombok.extern.slf4j.Slf4j;
import Database.Item;


public class ScannerUnit {
    private final RotationUnit rotationUnit = new RotationUnit();

    public boolean scanItem(Item item) {
        int attempt = 0;

        while (!item.isBarcodeVisible() && attempt < 4) { // Max 4 attempts (360°)

            rotationUnit.rotateItem(item);
            attempt++;
        }

        if (item.isBarcodeVisible()) {

            return true;
        } else {

            return false;
        }
    }
}

