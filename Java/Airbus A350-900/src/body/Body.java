package body;

import cabin.Cabin;
import wing.Wing;
import wing.WingPosition;
import main.Airplane;

public class Body {
    private Wing leftwing;
    private Wing rightwing;
    private Cabin cabin;

    public Body() {
        cabin = new Cabin();
    }

    public void addLeftWing(Wing wing) {
        leftwing = wing;
        leftwing.setPosition(WingPosition.LEFT);
    }
    public void addRightWing(Wing wing) {
        rightwing = wing;
        rightwing.setPosition(WingPosition.RIGHT);
    }
}
