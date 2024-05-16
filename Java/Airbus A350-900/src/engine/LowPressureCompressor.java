package engine;

public class LowPressureCompressor {
    private Unit frontUnit;
    private Unit rearUnit;
    private LowPressureShaft lowPressureShaft;

    public LowPressureCompressor() {
        frontUnit = new Unit(36, "M");
        rearUnit = new Unit(36, "M");
        lowPressureShaft = new LowPressureShaft(frontUnit, rearUnit);
    }
}
