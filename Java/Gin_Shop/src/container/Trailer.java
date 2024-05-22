package container;

public class Trailer {
    private Pallet[][][] storageArea;


    public Trailer() {
        storageArea = new Pallet[2][8][2];
    }

    public boolean addPallet(Pallet pallet) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 2; k++) {
                    if (storageArea[i][j][k] == null) {
                        storageArea[i][j][k] = pallet;
                        return true;
                    }
                }
            }
        } return false;
    }

    public boolean isFull() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 2; k++) {
                    if (storageArea[i][j][k] == null) {
                        return false;
                    }
                }
            }
        } return true;
    }
}

