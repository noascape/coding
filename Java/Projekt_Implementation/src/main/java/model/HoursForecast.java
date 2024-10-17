package model;

public class HoursForecast {
    private HourState[] hourForecasts;

    public HoursForecast(HourState[] hourForecasts) {
        this.hourForecasts = hourForecasts;
    }

    public HourState[] getHourForecasts() {
        return hourForecasts;
    }

    // Getter und Setter
}