package Database;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.Random;

@Data
@Slf4j
public class Item {
    private String label;
    private String barcode;
    private RecyclingType recyclingType;
    private MaterialType materialType;
    private Double depositAmount;
    private Side currentSide;
    private boolean compressed;

    public Item(String label, String barcode, RecyclingType recyclingType, MaterialType materialType, Double depositAmount) {
        this.label = label;
        this.barcode = barcode;
        this.recyclingType = recyclingType;
        this.materialType = materialType;
        this.depositAmount = depositAmount;
        this.currentSide = Side.values()[new Random().nextInt(Side.values().length)];
        this.compressed = false;
    }

    public boolean isBarcodeVisible() {
        return this.currentSide == Side.BACK; //you could use a similar method to show that the label is on the front side
    }
}


