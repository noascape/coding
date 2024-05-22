package container;

import bottle.Bottle;
import track.Track;

public class Pallet {
    private Box[] storageArea;
    private String palletLabel;
    private Track storeTrack;

    public Pallet(String palletLabel) {
        storageArea = new Box[3];
        this.palletLabel = palletLabel;

    }

    public boolean addBox(Box box) {
        for (int i = 0; i < storageArea.length; i++) {
                if (storageArea[i] == null) {
                    storageArea[i] = box;
                    return true;
                }
            }

        return false; //
    }

    public boolean isFull() {
        for (int i = 0; i < storageArea.length; i++) {
                if (storageArea[i] == null) {
                    return false;
                }
            }

        return true;
    }

}
