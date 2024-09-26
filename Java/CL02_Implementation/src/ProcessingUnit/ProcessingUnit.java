package ProcessingUnit;

public class ProcessingUnit {
    private final Processor processor;
    private final Core[] cores;

    public ProcessingUnit(Processor processor, Core[] cores) {
        this.processor = processor;
        this.cores = cores;
    }

    public Processor getProcessor() {
        return processor;
    }
}
