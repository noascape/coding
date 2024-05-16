package engine;

public class LowPressureTurbine {
    private Unit frontUnit;
    private Unit rearUnit;
    private LowPressureShaft lowPressureShaft;

    public LowPressureTurbine() {
        frontUnit = new Unit(36, "M");
        rearUnit = new Unit(36, "M");
        lowPressureShaft = new LowPressureShaft(frontUnit, rearUnit);
    }
}
