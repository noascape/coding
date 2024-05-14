package wing;

public class Wing {
    private Aileron aileron;

    public Wing(){
        aileron = new Aileron();
    }

    private WingPosition position;

    public WingPosition getPosition() {
        return position;
    }

    public void setPosition(WingPosition position) {
        this.position = position;
    }

    public void aileronUp(){
        aileron.up();
    }

    public void aileronDown(){
        aileron.down();
    }
}
