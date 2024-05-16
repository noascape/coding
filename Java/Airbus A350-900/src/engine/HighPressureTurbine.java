package engine;

public class HighPressureTurbine {
    private Unit frontUnit;
    private Unit rearUnit;
    private HighPressureShaft highPressureShaft;

    public HighPressureTurbine() {
        frontUnit = new Unit(36, "M");
        rearUnit = new Unit(36, "M");
        highPressureShaft = new HighPressureShaft(frontUnit, rearUnit);
    }
}
