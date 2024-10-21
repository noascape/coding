package model;

import lombok.Getter;

@Getter
public class DaysForecast {
    private final DayState[] dailyForecasts;

    public DaysForecast(DayState[] dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }

}
