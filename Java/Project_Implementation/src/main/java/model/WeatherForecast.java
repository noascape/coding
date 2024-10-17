package model;

import lombok.Getter;

@Getter
public class WeatherForecast {
    private final HoursForecast hoursForecast;
    private final DaysForecast daysForecast;

    public WeatherForecast(HoursForecast hoursForecast, DaysForecast daysForecast) {
        this.hoursForecast = hoursForecast;
        this.daysForecast = daysForecast;
    }


}