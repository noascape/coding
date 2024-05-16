package engine;

public class JetEngine {
    private Fan fan;
    private LowPressureCompressor lowPressureCompressor;
    private LowPressureTurbine lowPressureTurbine;
    private HighPressureTurbine highPressureTurbine;
    private HighPressureCompressor highPressureCompressor;
    private  CombustionChamber combustionChamber;

    public JetEngine() {
        fan = new Fan();
        lowPressureCompressor = new LowPressureCompressor();
        lowPressureTurbine = new LowPressureTurbine();
        highPressureTurbine = new HighPressureTurbine();
        highPressureCompressor = new HighPressureCompressor();
        combustionChamber = new CombustionChamber();
    }
}
