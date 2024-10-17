package model;

public class WeatherForecast {
    private HoursForecast hoursForecast;
    private DaysForecast daysForecast;

    public WeatherForecast(HoursForecast hoursForecast, DaysForecast daysForecast) {
        this.hoursForecast = hoursForecast;
        this.daysForecast = daysForecast;
    }

    public HoursForecast getHoursForecast() {
        return hoursForecast;
    }

    public DaysForecast getDaysForecast() {
        return daysForecast;
    }


}