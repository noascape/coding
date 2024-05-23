package container;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
import bottle.Bottle;

public class Box {
    private Bottle[][] storageArea;
    private UUID boxLabel;
    private String product;
    private int countBottle;
    private final ArrayList<UUID> usedBoxLabels = new ArrayList<UUID>();

    private List<Bottle> bottle;

    public Box(UUID boxLabel) {
        storageArea = new Bottle[3][3];
        this.boxLabel = boxLabel;
        usedBoxLabels.add(boxLabel);

    }

    public boolean addBottle(Bottle bottle) {
        for (int i = 0; i < storageArea.length; i++) {
            for (int j = 0; j < storageArea[i].length; j++) {
                if (storageArea[i][j] == null) {
                    storageArea[i][j] = bottle;
                    countBottle++;
                    product = bottle.getFrontLabelHeader(bottle);
                    return true;
                }
            }
        }
        return false; //
    }

    public boolean isFull() {
        for (int i = 0; i < storageArea.length; i++) {
            for (int j = 0; j < storageArea[i].length; j++) {
                if (storageArea[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    //Testmanagement
    public List<Bottle> getBottles() {
        List<Bottle> bottles = new ArrayList<>();
        for (int i = 0; i < storageArea.length; i++) {
            for (int j = 0; j < storageArea[i].length; j++) {
                if (storageArea[i][j] != null) {
                    bottles.add(storageArea[i][j]);
                }
            }
        }
        return bottles;
    }
}