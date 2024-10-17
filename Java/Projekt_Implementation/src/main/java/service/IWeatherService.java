package service;

import model.Location;
import model.DaysForecast;
import model.HoursForecast;

public interface IWeatherService {
    DaysForecast getDailyForecast(Location location);
    HoursForecast getHourForecast(Location location);
}
