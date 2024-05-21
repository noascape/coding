package container;

import java.util.ArrayList;
import java.util.UUID;
import bottle.Bottle;

public class Box {
    private Bottle[][] storageArea;
    private UUID boxLabel;
    private String product;
    private int countBottle;
    private final ArrayList<UUID> usedBoxLabels = new ArrayList<UUID>();

    public Box(UUID boxLabel) {
        storageArea = new Bottle[3][3];

        //if (){
        //this.boxLabel = boxLabel;
        //usedBoxLabels.add(boxLabel);
           // else {
                //    this.boxLabel = UUID.randomUUID();
                 //   usedBoxLabels.add(boxLabel);
                 //   }

       //}
    }

    public boolean addBottle(Bottle bottle) {
        for (int i = 0; i < storageArea.length; i++) {
            for (int j = 0; j < storageArea[i].length; j++) {
                if (storageArea[i][j] == null) {
                    storageArea[i][j] = bottle;
                    countBottle++;
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

    public Bottle[][] getStorageArea() {
        return storageArea;
    }

    public void setStorageArea(Bottle[][] storageArea) {
        this.storageArea = storageArea;
    }
}