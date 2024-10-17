package model;

public class DaysForecast {
    private DayState[] dailyForecasts;

    public DaysForecast(DayState[] dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }

    public DayState[] getDailyForecasts() {
        return dailyForecasts;
    }

}
