package states;

import main.WeatherApp;
import model.Location;

public interface State {
    void handleRequest(WeatherApp app, Location location);
}
