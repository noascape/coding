package bottle;

public class Bottle {

    String name = "Lab Gin 2008";
    int content = 500;
    String mouthpiece = "CARNETTE";
    float height = 164.5f;
    int diameter = 86;
    int marginalCapacity = 545;
    int weight = 400;
    boolean isFilled;
    int serialNumber;
    private static int serialCounter = 1;
    private FrontLabel frontLabel;
    private BackLabel backLabel;

    public void fill(){
        isFilled = true;
    }

    public void addFrontLabel(FrontLabel label) {
        label.setPosition(LabelPosition.FRONT);
        frontLabel = label;
    }

    public void addBackLabel(BackLabel label) {
        label.setPosition(LabelPosition.BACK);
        backLabel = label;
    }

    public String getFrontLabelHeader(Bottle bottle){
        return frontLabel.header;
    }

    public Bottle() {
        this.serialNumber = serialCounter++;
        if (Identity.isDuplicateSerialNumber(this.serialNumber)) {
            throw new IllegalStateException("Duplicate serial number" + this.serialNumber);
        }
    }

    public int getserialNumber() {
        return this.serialNumber;
    }

    //Testmanagement
    public boolean isFilled() {
        return this.isFilled;
    }
}
