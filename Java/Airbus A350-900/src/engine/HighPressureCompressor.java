package engine;

public class HighPressureCompressor {
    private Unit unit1;
    private Unit unit2;
    private Unit unit3;
    private Unit unit4;
    private Unit unit5;
    private Unit unit6;
    private HighPressureShaft highPressureShaft;

    public HighPressureCompressor() {
        unit1 = new Unit(36, "S");
        unit2 = new Unit(36, "S");
        unit3 = new Unit(32, "S");
        unit4 = new Unit(32, "S");
        unit5 = new Unit(30, "S");
        unit6 = new Unit(30, "S");
        highPressureShaft = new HighPressureShaft(unit1, unit6);
    }
}
