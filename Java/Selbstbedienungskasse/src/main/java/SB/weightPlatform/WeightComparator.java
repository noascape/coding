package SB.weightPlatform;

import java.util.Comparator;
import customer.*;
import database.DatabaseService;

public class WeightComparator implements Comparator<Item>{
    public final DatabaseService databaseService;

    public WeightComparator(DatabaseService databaseService) {this.databaseService = databaseService;}

    @Override
    public int compare(Item a1, Item a2) {
        float weight1 = a1.getWeight();
        float weight2 = a2.getWeight();
        return Float.compare(weight1, weight2);
    }

    public boolean verifyWeight(String barcode, float actualWeight) {
        Item item = databaseService.getItemByBarcode(barcode);
        if (item == null) return false;
        return Math.abs(item.getWeight() - actualWeight) < 0.05;
    }
}
