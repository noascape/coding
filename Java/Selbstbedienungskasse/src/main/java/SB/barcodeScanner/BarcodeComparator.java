package SB.barcodeScanner;

import customer.Item;
import java.util.Comparator;

public class BarcodeComparator implements Comparator<Item>{

    @Override
    public int compare(Item a1, Item a2) {
        return a1.getBarcode().compareTo(a2.getBarcode());
    }
}
