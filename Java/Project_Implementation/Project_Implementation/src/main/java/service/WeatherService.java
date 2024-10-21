package service;

import api.WeatherAPI;
import model.*;

import java.time.LocalDateTime;

public class WeatherService implements IWeatherService {
    private final WeatherAPI weatherAPI;

    public WeatherService(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    @Override
    public DaysForecast getDailyForecast(Location location) {
        DayState[] dailyForecasts = new DayState[4];

        LocalDateTime baseTime = LocalDateTime.now().withHour(14).withMinute(0).withSecond(0).withNano(0);

        for (int i = 0; i < 4; i++) {
            LocalDateTime forecastTime = baseTime.plusDays(i);

            dailyForecasts[i] = weatherAPI.getWeatherAsDay(location, forecastTime);
        }

        return new DaysForecast(dailyForecasts);
    }

    @Override
    public HoursForecast getHourForecast(Location location) {
        HourState[] hourlyForecasts = new HourState[4];

        LocalDateTime baseTime = LocalDateTime.now();

        for (int i = 0; i < 4; i++) {

            LocalDateTime forecastTime = baseTime.plusHours(i);

            hourlyForecasts[i] = weatherAPI.getWeatherAsHour(location, forecastTime);
        }
        return new HoursForecast(hourlyForecasts);
    }
}