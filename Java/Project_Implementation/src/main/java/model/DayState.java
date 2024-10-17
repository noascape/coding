package model;

import java.time.LocalDateTime;

public class DayState extends WeatherState {
    private LocalDateTime sunrise;
    private LocalDateTime sunfall;


    public DayState(Location location, float temperature, float rainfall, float windspeed, float clouds, LocalDateTime time, LocalDateTime sunrise, LocalDateTime sunfall) {
        super(location, temperature, rainfall, windspeed, clouds, time);
        this.sunrise = sunrise;
        this.sunfall = sunfall;
    }


    public LocalDateTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalDateTime getSunfall() {
        return sunfall;
    }

    public void setSunfall(LocalDateTime sunfall) {
        this.sunfall = sunfall;
    }
}
