package service;

import model.DaysForecast;
import model.HoursForecast;
import model.Location;

public interface IWeatherService {
    DaysForecast getDailyForecast(Location location);

    HoursForecast getHourForecast(Location location);
}
