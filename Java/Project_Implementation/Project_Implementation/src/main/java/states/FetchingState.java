package states;

import main.WeatherApp;
import model.Location;

public class FetchingState implements State {
    @Override
    public void handleRequest(WeatherApp app, Location location) {
        System.out.println("Fetching weather data...");
        try {
            app.getWeatherForecast(location);
            app.setState(new SuccessState());
            app.requestWeather(location);
        } catch (Exception e) {
            app.setState(new ErrorState());
        }
    }
}
