package states;

import main.WeatherApp;
import model.Location;

public class IdleState implements State {
    @Override
    public void handleRequest(WeatherApp app, Location location) {
        System.out.println("Starting request for weather data...");
        app.setState(new FetchingState());
        app.requestWeather(location);
    }
}
