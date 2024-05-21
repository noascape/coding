package bottle;

import java.util.ArrayList;
import java.util.Random;

public class Identity {

    private final ArrayList<Integer> usedSerialNumbers = new ArrayList<Integer>();

        //return String.valueOf(number);

    public boolean equals (Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof final Bottle bottle)) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        return true;
        //return serialNumber.equals(bottle.getserialNumber());
    }
    }




