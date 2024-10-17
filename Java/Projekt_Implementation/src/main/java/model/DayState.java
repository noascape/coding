package model;

import java.time.LocalDateTime;

public class DayState extends WeatherState {
    private LocalDateTime sunrise;
    private LocalDateTime sunfall;

    // Konstruktor
    public DayState(Location location, float temperature, float rainfall, float windspeed, float clouds, LocalDateTime time, LocalDateTime sunrise, LocalDateTime sunfall) {
        super(location, temperature, rainfall, windspeed, clouds, time);  // Aufruf des Konstruktors der Superklasse
        this.sunrise = sunrise;
        this.sunfall = sunfall;
    }

    // Getter und Setter für sunrise und sunfall
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
