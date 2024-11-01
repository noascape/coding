package RotationUnit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import Database.Item;

@AllArgsConstructor
@Slf4j
public class Scanner {
    private final RotationUnit rotationUnit;


    public String scanItem(Item item) {
        int attempt = 0;

        while (!item.isBarcodeVisible() && attempt < 4) {
            log.info("Attempting to scan item on the {} side failed", item.getCurrentSide());
            rotationUnit.rotateItem(item);
            attempt++;
        }

        if (item.isBarcodeVisible()) {
            log.info("Barcode detected for item");  //muss ich aus logischen Gründen am Schluss noch entfernen
            return item.getBarcode();
        } else {
            log.warn("Failed to detect barcode for item");
            return null;
        }
    }
}



