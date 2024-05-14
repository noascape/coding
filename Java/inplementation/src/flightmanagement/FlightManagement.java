package flightmanagement;

import main.Glider;

public class FlightManagement implements IFlightManagement{
    private Glider glider;

    public FlightManagement(Glider glider){
        this.glider = glider;
    }

    @Override
    public void executeClimb() {
        glider.aileronUp();
    }

    @Override
    public void executeSink() {
        glider.aileronDown();
    }

    @Override
    public void executeTurnLeft() {

    }

    @Override
    public void executeTurnLeft(int degrees) {

    }

    @Override
    public void executeTurnRight() {

    }

    @Override
    public void executeTurnRight(int degrees) {

    }
}
