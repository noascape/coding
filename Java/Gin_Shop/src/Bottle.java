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
    private Label frontLabel;
    private Label backLabel;



    public void fill(){
        isFilled = true;
    }

    public void addFrontLabel(Label label) {
        label.setPosition(LabelPosition.FRONT);
        frontLabel = label;
    }

    public void addBackLabel(Label label) {
        label.setPosition(LabelPosition.BACK);
        backLabel = label;
    }



    //Getter and Setter
    public Label getBackLabel() {
        return backLabel;
    }

    public void setBackLabel(Label backLabel) {
        this.backLabel = backLabel;
    }

    public Label getFrontLabel() {
        return frontLabel;
    }

    public void setFrontLabel(Label frontLabel) {
        this.frontLabel = frontLabel;
    }
}
