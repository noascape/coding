package states;

import main.WeatherApp;
import model.Location;

public class SuccessState implements State {
    @Override
    public void handleRequest(WeatherApp app, Location location) {
        System.out.println("Weather data successfully fetched and processed.");
        app.setState(new IdleState());
    }
}
