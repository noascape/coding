package model;

import lombok.Getter;

@Getter
public class HoursForecast {
    private final HourState[] hourForecasts;

    public HoursForecast(HourState[] hourForecasts) {
        this.hourForecasts = hourForecasts;
    }

}