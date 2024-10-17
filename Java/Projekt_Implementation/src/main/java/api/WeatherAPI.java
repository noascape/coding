package api;

import model.DayState;
import model.HourState;
import model.Location;
import java.time.LocalDateTime;


public class WeatherAPI {
    private final String apiKey;

    public WeatherAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public DayState getWeatherAsDay(Location location, LocalDateTime time) {
        return null;
    }
    public HourState getWeatherAsHour(Location location, LocalDateTime time) {
        return null;
    }
}
