package cockpit;

import flightmanagement.FlightManagement;

public class ControlStick {
    private FlightManagement flightManagement;

    public ControlStick(FlightManagement flightManagement) {
        this.flightManagement = flightManagement;
    }



    public void pull(){
        flightManagement.executeClimb();
    }
    public void push(){
        flightManagement.executeSink();
    }
}
