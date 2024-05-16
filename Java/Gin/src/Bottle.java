import java.util.ArrayList;
import java.util.Random;

public class Bottle {
    private String name = "Lab Gin 2008";
    private int content = 500;
    private String mouthPiece = "CARNETTE";
    private double height = 164.5;
    private int diameter = 85;
    private int marginalCapacity = 545;
    private int weight = 400;
    private boolean isFilled;
    private String serialNumber;
    private Identity identity;

    // Constructor
    public Bottle(Identity identity) {
        
        this.identity = identity;
        this.serialNumber = identity.generateSerialNumber();

    }

    public void fill() {
        isFilled = true;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public String getNumber () {
        return serialNumber;
    }


    //Identity
    public boolean equals (Object object){
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
        return serialNumber.equals(bottle.getNumber());
    }

}