package engine;

public class Fan {
    private Unit frontUnit;
    private Unit rearUnit;
    private LowPressureShaft lowPressureShaft;

    public Fan() {
        frontUnit = new Unit(48, "L");
        rearUnit = new Unit(45, "L");
        lowPressureShaft = new LowPressureShaft(frontUnit, rearUnit);
    }
}
