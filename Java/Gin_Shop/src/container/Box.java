package container;

import bottle.Bottle;

public class Box {
    private Bottle[][] storageArea;
    private String boxLabel;
    private String product;
    private int bottleAmount;

    public Box() {
        storageArea = new Bottle[3][3];
    }

    public boolean addBottle(Bottle bottle) {
        for (int i = 0; i < storageArea.length; i++) {
            for (int j = 0; j < storageArea[i].length; j++) {
                if (storageArea[i][j] == null) {
                    storageArea[i][j] = bottle;
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