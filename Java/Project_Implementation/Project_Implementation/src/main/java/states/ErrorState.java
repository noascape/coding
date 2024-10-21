package states;

import main.WeatherApp;
import model.Location;

public class ErrorState implements State {
    @Override
    public void handleRequest(WeatherApp app, Location location) {
        System.out.println("An error occurred. Returning to idle state.");
        app.setState(new IdleState());  // Nach Fehler zurück in IdleState
    }
}
