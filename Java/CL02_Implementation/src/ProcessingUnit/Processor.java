package ProcessingUnit;

public class Processor {
    private final Core[] cores;

    public Processor(Core[] cores) {
        this.cores = cores;
    }

    public Core[] getCores() {
        return cores;
    }

}
