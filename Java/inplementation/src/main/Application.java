package main;

import cockpit.ControlStick;
import cockpit.Seat;
import flightmanagement.FlightManagement;
import shared.Configuration;
import wing.Wing;

public class Application {
    public static void buildSeats(Glider glider) {
        for (int i = 0; i < Configuration.INSTANCE.countSeats; i++) {
            glider.setSeat(new Seat(), i);
        }
    }

    public static void main(String... args) {
        System.out.println("Welcome | Glider");

        Glider glider = new Glider();
        FlightManagement flightManagement = new FlightManagement(glider);
        ControlStick controlStick = new ControlStick(flightManagement);

        glider.addControlStick(controlStick);

        buildSeats(glider);

        glider.addWings(new Wing(), new Wing());


        controlStick.pull();
        controlStick.push();
    }
}
